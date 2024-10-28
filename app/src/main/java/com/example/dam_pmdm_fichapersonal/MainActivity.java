package com.example.dam_pmdm_fichapersonal;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Creamos una variable para saber que curso se ha seleccionado, si es -1 no aparecerá ninguno seleccionado
    int selectedCourse = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portrait);

        //(findViewById(R.id.btn_info1)).setOnClickListener(v->());
        (findViewById(R.id.btn_info2)).setOnClickListener(v->elegirCurso());
        //(findViewById(R.id.btn_info3)).setOnClickListener(v->());
        //(findViewById(R.id.btn_clear)).setOnClickListener(v->());

    }

    private void elegirCurso() {

        final String[] listItems = new String[]{"DAM","DAW","ASIR"};

        new AlertDialog.Builder(MainActivity.this)
               .setTitle(R.string.titleSC)
               .setCancelable(false)
               .setSingleChoiceItems(listItems, selectedCourse, (dialog, which) -> {
                   selectedCourse = which;
        })
                .setNegativeButton(R.string.OptionSC1, null)
                .setPositiveButton(R.string.OptionSC2, (dialog,which)->mostrarCurso(selectedCourse,listItems))
                .create()
                .show();
    }
    private void mostrarCurso(int checkedItem,String[] listItems ){
       if(checkedItem>=0){
           String cursoSeleccionado = listItems[checkedItem];
           ((TextView)findViewById(R.id.lbl_info2)).setText(cursoSeleccionado);
       }
    }

}