package change.com.animationwithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity {

    EditText to,subject,message;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        to = findViewById(R.id.edit_text_to);
        subject = findViewById(R.id.edit_text_subject);
        message = findViewById(R.id.edit_text_message);
        btn = findViewById(R.id.button_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String recipientList = to.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject1 = subject.getText().toString();
        String message1 = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject1);
        intent.putExtra(Intent.EXTRA_TEXT,message1);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }
}
