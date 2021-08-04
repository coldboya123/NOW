package com.example.now.Model.ApiService;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.now.R;
import com.example.now.View.MainView.MainActivity;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class ListenNotificationService extends FirebaseMessagingService {

    private String CHANNEL_ID = "CHANEL_APP";
    private NotificationManager manager;

    public ListenNotificationService() {
    }

//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//       if (remoteMessage.getNotification() != null){
//           FirebaseMessaging.getInstance().subscribeToTopic("myTopic");
//           Intent intent = new Intent(this, MainActivity.class);
//           intent.putExtra("tab", 1);
//           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//           PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//           NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
//           builder.setContentIntent(pendingIntent);
//           builder.setSmallIcon(R.drawable.logo_now);
//           builder.setShowWhen(true);
//           builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo_now));
//           builder.setContentTitle("Đơn hàng hoàn tất");
//           builder.setContentText("Tài xế đã giao đơn hàng cho bạn, Vui lòng đánh giá và thưởng thức món ăn nhé!");
//           builder.setPriority(NotificationCompat.PRIORITY_HIGH);
//           builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
//           builder.setLights(Color.RED, 3000, 3000);
//           builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
//
//           manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//               NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
//               notificationChannel.setSound(null, null);
//               manager.createNotificationChannel(notificationChannel);
//           }
//           manager.notify(1,builder.build());
//       }
//    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            Log.d("bbb", "onMessageReceived: " + remoteMessage.getNotification().getBody());
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            sendNotification(title,message);
        }
    }
    @SuppressLint("CommitPrefEdits")
    private void sendNotification(String title, String message) {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            channel.setDescription("Sample Channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000});
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setShowWhen(true)
                .setSmallIcon(R.drawable.logo_now)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setLights(Color.RED, 3000, 3000)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        manager.notify(1, builder.build());
    }

}