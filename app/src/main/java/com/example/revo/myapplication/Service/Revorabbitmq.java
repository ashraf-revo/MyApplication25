package com.example.revo.myapplication.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;

import com.example.revo.myapplication.NotificationReceiver;
import com.example.revo.myapplication.R;
import com.example.revo.myapplication.model.Person;
import com.example.revo.myapplication.model.event;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by revo on 18/11/15.
 */
public class Revorabbitmq {
    private String host;
    private String username;
    private String password;
    private String listen;
    Context context;
    Person person;
    private String queue = "toServer";
    private ConnectionFactory connectionFactory;
    private Connection connection;
    Channel channel;
    private Consumer consumer;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void init(String host, String username, String password, final String listen, final Context context, Person person) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.listen = listen;
        this.context = context;
        this.person = person;
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                connectionFactory = getconnectionFactory();
                connection = getConnection();
                channel = getchannel();
                try {
                    consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                                throws IOException {
                            String message = new String(body, "UTF-8");

                            Intent intent = new Intent(context, NotificationReceiver.class);
                            event event = new event().setName(message).setEmail(message);
                            intent.putExtra("key", event);
                            PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
                            Notification noti = new Notification.Builder(context)
                                    .setContentTitle(message)
                                    .setContentText(message).setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentIntent(pIntent)
                                    .addAction(R.mipmap.ic_launcher, "Call", pIntent)
                                    .addAction(R.mipmap.ic_launcher, "More", pIntent)
                                    .addAction(R.mipmap.ic_launcher, "And more", pIntent).build();
                            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                            noti.flags |= Notification.FLAG_AUTO_CANCEL;
                            notificationManager.notify(0, noti);
                        }
                    };
                    channel.basicConsume(listen, true, consumer);
                } catch (IOException ignored) {
                }
                SendLocation();

                return null;
            }
        }.execute();
    }

    private ConnectionFactory getconnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setVirtualHost(username);
        factory.setPassword(password);
        return factory;
    }

    private Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException ignored) {
        }
        return null;
    }

    private Channel getchannel() {
        try {
            return connection.createChannel();
        } catch (IOException ignored) {
        }
        return null;
    }

    void SendLocation() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                try {
                    int y = (int) (Math.random() * 100);
                    int x = (int) (Math.random() * 100);
                    String s = "id=" + person.getId() + ",x=" + x + ",y=" + y + "";
                    channel.basicPublish("", queue, null, s.getBytes());
                } catch (IOException ignored) {
                }
            }
        });

    }
    void SendMessage(final String queuename, final byte[] message) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    channel.basicPublish("", queuename, null, message);
                } catch (IOException ignored) {
                }
                return null;
            }
        };
    }

    void closeConnection() {
        try {
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException ignored) {
        }
    }

}
