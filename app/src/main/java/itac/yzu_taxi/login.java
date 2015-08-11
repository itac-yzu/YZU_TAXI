package itac.yzu_taxi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChengYuShao on 15/8/10.
 */
public class login extends ActionBarActivity {
    LinearLayout linearLayout_login;
    EditText editText_email;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        init();
        startAnimatation();
        setupButton();
    }

    void init(){
        linearLayout_login = (LinearLayout) this.findViewById(R.id.linearLayout_login);
        editText_email = (EditText) this.findViewById(R.id.login_editText_email);
        button_login = (Button) this.findViewById(R.id.login_button);
    }

    private void startAnimatation(){
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(400);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(300);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        linearLayout_login.setLayoutAnimation(controller);
    }

    private void setupButton(){
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_email.setError(null);
                final String inputEmail = editText_email.getText().toString();
                if (!isValidEmail(inputEmail)){
                    editText_email.setError("Invaild email address.");
                }
                else {
                    final Dialog dialog = new Dialog(login.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_dialog_login);
                    dialog.show();

                    final TextView textView_diacancel = (TextView) dialog.findViewById(R.id.loginDialog_textView2);
                    textView_diacancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                            Intent intent = new Intent(login.this,signup.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.animation_slidingout,R.anim.animation_slidingin);
                        }
                    });
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "((s[0-9]{6,7}@(mail)\\.yzu\\.edu\\.tw)|(\\w[-\\w.+]*@saturn\\.yzu\\.edu\\.tw))";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
