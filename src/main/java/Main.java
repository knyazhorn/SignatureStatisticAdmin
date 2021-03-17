import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("START");

        FileInputStream token = null;
        try {
            //token = new FileInputStream("signature-95c26-2a1a2e488729.json");
            token = new FileInputStream("/home/ivan_horniichuk/signature-95c26-firebase-adminsdk-1moqc-b89c07482d.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(token))
                    .setDatabaseUrl("https://signature-95c26.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Auth Success");

            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("devices");
            System.out.println(ref);
            ref.child("test").setValue("sdfgsdfsdf", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref1) {
                    System.out.println("XXXX");
                }
            });


            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object document = dataSnapshot.getValue();
                    System.out.println("onDataChange()");
                    System.out.println(document);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println("onCancelled()");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
