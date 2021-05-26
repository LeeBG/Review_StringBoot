package com.cos.phoneapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    private ConstraintLayout clayout;
    private FloatingActionButton fab;
    private List<Phone> phones = new ArrayList<>();
    private RecyclerView rvPhone;
    private PhoneAdapter adapter;
    private RoundedImageView rvProfile;
    private PhoneService phoneService = PhoneService.retrofit.create(PhoneService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addPhone();
    }

    public void init() {  //초기화
        clayout = findViewById(R.id.main_layout);
        fab = findViewById(R.id.fab_email);
        rvPhone = findViewById(R.id.rv_phone);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rvPhone.getContext(),new LinearLayoutManager(this).getOrientation());
        rvPhone.addItemDecoration(dividerItemDecoration);

        rvPhone.setLayoutManager(manager);
        download();
    }

    public void download(){
        // 다운로드
        Call<CMRespDto<List<Phone>>> call = phoneService.findAll();

        call.enqueue(new Callback<CMRespDto<List<Phone>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Phone>>> call, Response<CMRespDto<List<Phone>>> response) {
                CMRespDto<List<Phone>> cmRespDto = response.body();
                phones = cmRespDto.getData();
                //어댑터에게 남기기
                Log.d(TAG, "onResponse: 응답받은 데이터" + phones.toString());
                adapter = new PhoneAdapter(MainActivity.this,phones);
                rvPhone.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Phone>>> call, Throwable t) {
                Log.d(TAG, "onFailure: findAll() 실패");
            }
        });
    }

    //fab클릭 이벤트 주소 더하기
    public void addPhone() {
        fab.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add_phone, null);

            final EditText etName = dialogView.findViewById(R.id.et_name);
            final EditText etTel = dialogView.findViewById(R.id.et_tel);

            // 갤러리 사진 가져오기
            rvProfile = dialogView.findViewById(R.id.rv_profile);

            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setTitle("연락처 등록");
            dlg.setView(dialogView);
            dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    savePhone(new Phone(null, etName.getText().toString(), etTel.getText().toString()));
                }
            });
            dlg.setNegativeButton("닫기", null);
            dlg.show();
        });
    }

    private void savePhone(Phone phone) {
        Call<CMRespDto<Phone>> call = phoneService.save(phone);
        call.enqueue(new Callback<CMRespDto<Phone>>() {
            @Override
            public void onResponse(Call<CMRespDto<Phone>> call, Response<CMRespDto<Phone>> response) {
                CMRespDto<Phone> cmRespDto = response.body();
                if (cmRespDto.getCode() == 1) {
                    adapter.addPhone(cmRespDto.getData());
                } else {
                    Snackbar.make(clayout, "save 실패.", 1000).show();
                }
            }

            @Override
            public void onFailure(Call<CMRespDto<Phone>> call, Throwable t) {
                Snackbar.make(clayout, "save 실패", 1000).show();
            }
        });
    }

    public void updatePhone(Long id, Phone phone, int position) {
        Call<CMRespDto<Phone>> call = phoneService.putPhone(id, phone);
        call.enqueue(new Callback<CMRespDto<Phone>>() {
            @Override
            public void onResponse(Call<CMRespDto<Phone>> call, Response<CMRespDto<Phone>> response) {
                CMRespDto<Phone> cmRespDto = response.body();
                if (cmRespDto.getCode() == 1) {
                    adapter.updatePhone(position, cmRespDto.getData());
                } else {
                    Snackbar.make(clayout, "저장 실패", 1000).show();
                }
            }

            @Override
            public void onFailure(Call<CMRespDto<Phone>> call, Throwable t) {
                Snackbar.make(clayout, "저장 실패", 1000).show();
            }
        });
    }

    public void deletePhone(Long id, int position) {
        Call<CMRespDto<Phone>> call = phoneService.delete(id);
        call.enqueue(new Callback<CMRespDto<Phone>>() {
            @Override
            public void onResponse(Call<CMRespDto<Phone>> call, Response<CMRespDto<Phone>> response) {
                CMRespDto<Phone> cmRespDto = response.body();
                if (cmRespDto.getCode() == 1) {
                    adapter.deletePhone(position);
                } else {
                    Snackbar.make(clayout, "저장 실패", 1000).show();
                }
            }

            @Override
            public void onFailure(Call<CMRespDto<Phone>> call, Throwable t) {
                Snackbar.make(clayout, "저장 실패", 1000).show();
            }
        });
    }
}