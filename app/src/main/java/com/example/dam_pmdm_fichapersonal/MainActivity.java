package com.example.dam_pmdm_fichapersonal;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Creamos una variable para saber que curso se ha seleccionado, si es -1 no aparecerá ninguno seleccionado
    int selectedCourse = -1;
    //Creamos una variable para saber cuantos lenguajes hay selecciados.

    boolean[] checkedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portrait);

        //(findViewById(R.id.btn_info1)).setOnClickListener(v->());
        (findViewById(R.id.btn_info2)).setOnClickListener(v->elegirCurso());
        (findViewById(R.id.btn_info3)).setOnClickListener(v->elegirLenguaje());
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

    private void elegirLenguaje(){
        final String [] listItems= new String []{"Java","JavaScript","C#","Kotlin","Python"};
        checkedItems = new boolean[listItems.length];
        new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.titleMC)
                .setCancelable(false)
                .setMultiChoiceItems(listItems,checkedItems,(dialog,which,isChecked)->{
                    checkedItems[which] = isChecked;
                })
                .setPositiveButton(R.string.Option2MC,(dialog,which)->mostrarLenguaje(checkedItems,listItems))
                .setNegativeButton(R.string.Option1MC,null)
                .setNeutralButton(R.string.Optiom3MC,null)
                .create()
                .show();
    }

    private void mostrarLenguaje(boolean[] checkedItems,String[]listItems){
        String lenguajeSeleccionado = "";
        for(int i=0;i<=listItems.length-1;i++){
            if(checkedItems[i]){
                if(lenguajeSeleccionado.isEmpty()){
                    lenguajeSeleccionado = listItems[i];
                }else{
                    lenguajeSeleccionado += "\n"+ listItems[i] ;
                }

            }
        }
        ((TextView)findViewById(R.id.lbl_info3)).setText(lenguajeSeleccionado);
    }

}