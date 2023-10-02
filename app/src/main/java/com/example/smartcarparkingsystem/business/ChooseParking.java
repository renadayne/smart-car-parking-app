package com.example.smartcarparkingsystem.business;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcarparkingsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChooseParking extends AppCompatActivity {
    Button button2, button3;
    ImageView parkingarea1, parkingarea2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_parking);
        Init();
        // xu li

        parkingarea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotiDialog();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(ChooseParking.this, R.style.AlertDialogTheme);
                builder.setTitle("Thông tin bãi đỗ");
                builder.setMessage("Địa chỉ/ Address : Quán rượu đuôi mèo, thành Monstadt, lục địa Teyvat\n" +
                        "Sỗ chỗ trong bãi đỗ : 8");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("Đã rõ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();*/
            }
        });

        parkingarea2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotiDialog2();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseParking.this, ParkingAreaClone.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseParking.this, ParkingArea2.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });

    }
    private  void showInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseParking.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ChooseParking.this).inflate(
                R.layout.layout_sucess_bg,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Parking Info");
        ((TextView) view.findViewById(R.id.textMessage)).setText("\n\nĐịa chỉ/ Address : Quán rượu đuôi mèo, thành Monstadt, lục địa Teyvat\nSỗ chỗ trong bãi đỗ : 8");

        ((Button) view.findViewById(R.id.buttonAction)).setText("Ok");
        //((ImageView) view.findViewById(R.id.imageIcon)).setImageResource();
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //Toast.makeText(ChooseParking.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    // alert dialog parking 1
    private  void showNotiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseParking.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ChooseParking.this).inflate(
                R.layout.layout_noti_bg,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Thông tin bãi đỗ");
        ((TextView) view.findViewById(R.id.textMessage)).setText("\n\nĐịa chỉ/ Address : 144 Xuân Thuỷ, Cầu Giấy, Hà Nội\nSỗ chỗ trong bãi đỗ : 8");

        ((Button) view.findViewById(R.id.buttonShowMap)).setText("Vị trí bãi");
        ((Button) view.findViewById(R.id.buttonBack)).setText("Trở về");
        //((ImageView) view.findViewById(R.id.imageIcon)).setImageResource();
        final AlertDialog alertDialog = builder.create();
        // nut chuyen huong sang man hinh map
        view.findViewById(R.id.buttonShowMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseParking.this, ParkingArea1Maps.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });


        // nut tro ve
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //Toast.makeText(ChooseParking.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    // alert dialog parking 2

    private  void showNotiDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseParking.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ChooseParking.this).inflate(
                R.layout.layout_noti_bg,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Thông tin bãi đỗ");
        ((TextView) view.findViewById(R.id.textMessage)).setText("\n\nĐịa chỉ/ Address : 181 Lương Thế Vinh, Thanh Xuân, Hà Nội\nSỗ chỗ trong bãi đỗ : 10");

        ((Button) view.findViewById(R.id.buttonShowMap)).setText("Vị trí bãi");
        ((Button) view.findViewById(R.id.buttonBack)).setText("Trở về");
        //((ImageView) view.findViewById(R.id.imageIcon)).setImageResource();
        final AlertDialog alertDialog = builder.create();
        // nut chuyen huong sang man hinh map
        view.findViewById(R.id.buttonShowMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseParking.this, ParkingArea2Maps.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });


        // nut tro ve
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //Toast.makeText(ChooseParking.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    private void Init() {
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        parkingarea1 = (ImageView) findViewById(R.id.parkingarea1);
        parkingarea2 = (ImageView) findViewById(R.id.carparkingchuaedit);
    }
}