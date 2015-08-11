package itac.yzu_taxi;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ChengYuShao on 15/8/10.
 */
public class signup extends ActionBarActivity {
    LinearLayout linearLayout_poptextLayout,linearLayout_signup;
    EditText editText_Name,editText_Password,editText_Vailcode;
    TextView textView_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        init();
        startAnimatation(linearLayout_signup);
        setupList();
    }

    private void init(){
        linearLayout_poptextLayout = (LinearLayout) this.findViewById(R.id.signup_poptextLayout);
        linearLayout_signup = (LinearLayout) this.findViewById(R.id.signup_layout);
        editText_Name = (EditText) this.findViewById(R.id.signup_editText_name);
        editText_Password = (EditText) this.findViewById(R.id.signup_editText_password);
        editText_Vailcode = (EditText) this.findViewById(R.id.signup_editText_vailcode);
        textView_alert = (TextView) this.findViewById(R.id.signup_textView_);
    }

    private void setupList(){
        editText_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    startAnimatation(linearLayout_poptextLayout);
                    textView_alert.setText(R.string.signup_passalert);
                    textView_alert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Dialog dialog = new Dialog(signup.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_dialog_signup);
                            dialog.show();

                            final TextView textView_dialogCancel = (TextView) dialog.findViewById(R.id.signupDialog_Cancel);
                            textView_dialogCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.cancel();
                                }
                            });
                        }
                    });
                }
                else {
                    textView_alert.setText("");
                }
            }
        });
    }

    private void startAnimatation(LinearLayout resource){
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

//        animation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF, 0.0f
//        );
//        animation.setDuration(300);
//        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        resource.setLayoutAnimation(controller);
    }
}
