package silverassist.silverassist.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.bukkit.Bukkit;
import silverassist.silverassist.SilverAssist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Firebase {
    public static final String projectId = "punisherlist";
    public static Firestore db = null;
    public static void FirebaseInit() throws IOException {

        //メモ; ユーザ名！直す
      InputStream serviceAccount = new FileInputStream("C:\\Users\\<name>\\OneDrive\\デスクトップ\\Minecraft\\server\\1.18.1\\punisherlist-364008-3513ed286048.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }
}
