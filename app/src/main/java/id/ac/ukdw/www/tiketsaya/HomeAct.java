package id.ac.ukdw.www.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    LinearLayout btn_ticket_pisa,btn_ticket_torri,btn_ticket_pagoda,btn_ticket_borobudur,btn_ticket_sphinx,btn_ticket_monas;
    CircleView btn_to_profile;
    ImageView photo_home_user;
    TextView user_balance, nama_lengkap, bio;

    DatabaseReference reference;

    String USERNAME_KEY="usernamekey";
    String username_key="";
    String username_key_new="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //langsung mulai untuk mangambil username lokal
        getUsernameLocal();

        photo_home_user = findViewById(R.id.photo_home_user);
        user_balance = findViewById(R.id.user_balance);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);

        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_borobudur = findViewById(R.id.btn_ticket_borobudur);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_sphinx = findViewById(R.id.btn_ticket_sphinx);
        btn_ticket_torri = findViewById(R.id.btn_ticket_torri);
        btn_to_profile = findViewById(R.id.btn_to_profile);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("$"+dataSnapshot.child("user_balance").getValue().toString());

                Picasso.with(HomeAct.this).load(dataSnapshot.child("url_photo_profile").getValue().toString()).centerCrop().fit().into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(HomeAct.this,MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotototicket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotototicket.putExtra("jenis_tiket","Pisa");
                startActivity(gotototicket);
            }
        });

        btn_ticket_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotototicket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotototicket.putExtra("jenis_tiket","Torri");
                startActivity(gotototicket);
            }
        });

        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotototicket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotototicket.putExtra("jenis_tiket","Pagoda");
                startActivity(gotototicket);
            }
        });

        btn_ticket_borobudur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotototicket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotototicket.putExtra("jenis_tiket","Candi");
                startActivity(gotototicket);
            }
        });

        btn_ticket_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotototicket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotototicket.putExtra("jenis_tiket","Sphinx");
                startActivity(gotototicket);
            }
        });

        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotototicket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotototicket.putExtra("jenis_tiket","Monas");
                startActivity(gotototicket);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
