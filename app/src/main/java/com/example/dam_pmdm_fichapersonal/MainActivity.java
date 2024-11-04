package com.example.dam_pmdm_fichapersonal;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int selectedCourse = -1;
    int beforeSelected = selectedCourse;
    boolean[] checkedItems;
    boolean[] beforeCheckeds;
    final String [] listLanguages= new String []{"Java","JavaScript","C#","Kotlin","Python"};
    final String[] listCourse = new String[]{"DAM","DAW","ASIR"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portrait);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(getString(R.string.btn_info1));
            String course = savedInstanceState.getString(getString(R.string.btn_info2));
            String languages = savedInstanceState.getString(getString(R.string.btn_info3));
            selectedCourse = savedInstanceState.getInt("selectedCourse");
            checkedItems = savedInstanceState.getBooleanArray("checkedItems");

            ((TextView) findViewById(R.id.lbl_info1)).setText(name);
            ((TextView) findViewById(R.id.lbl_info2)).setText(course);
            ((TextView) findViewById(R.id.lbl_info3)).setText(languages);
        }

        (findViewById(R.id.btn_info1)).setOnClickListener(v->elegirNombre());
        (findViewById(R.id.btn_info2)).setOnClickListener(v->elegirCurso());
        (findViewById(R.id.btn_info3)).setOnClickListener(v->elegirLenguaje());
        (findViewById(R.id.btn_clear)).setOnClickListener(v->elegirnuevoRegistro());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.btn_info1),((TextView)findViewById(R.id.lbl_info1)).getText().toString());
        outState.putString(getString(R.string.btn_info2),((TextView)findViewById(R.id.lbl_info2)).getText().toString());
        outState.putString(getString(R.string.btn_info3),((TextView)findViewById(R.id.lbl_info3)).getText().toString());

        outState.putInt("selectedCourse",selectedCourse);
        outState.putBooleanArray("checkedItems",checkedItems);

    }
    private void elegirnuevoRegistro() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.titleAD)
                .setMessage(R.string.messageAD)
                .setNegativeButton(R.string.OptiomCL1,null)
                .setPositiveButton(R.string.OptiomCL2,(dialog,which)->clear())
                .create()
                .show();
    }
    private void cleanLanguages(){
        checkedItems = new boolean[listLanguages.length];
        beforeCheckeds = new boolean[listLanguages.length];
        ((TextView)findViewById(R.id.lbl_info3)).setText("");
    }

    private void clear(){
        selectedCourse=-1;
        beforeSelected=selectedCourse;
        checkedItems = new boolean[listLanguages.length];
        beforeCheckeds = new boolean[listLanguages.length];
        ((TextView)findViewById(R.id.lbl_info1)).setText("");
        ((TextView)findViewById(R.id.lbl_info2)).setText("");
        ((TextView)findViewById(R.id.lbl_info3)).setText("");

    }

    private void elegirNombre() {
        final View customlayout = getLayoutInflater().inflate(R.layout.customlayout,null);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.titleCL)
                .setView(customlayout)
                .setNegativeButton(R.string.OptiomCL1,null)
                .setPositiveButton(R.string.OptiomCL2,(dialog,which)->{
                    EditText editText = customlayout.findViewById(R.id.txt_name);
                    String name = editText.getText().toString().trim();
                    ((TextView)findViewById(R.id.lbl_info1)).setText(name);
                })
                .create()
                .show();
    }

    private void elegirCurso() {

        new AlertDialog.Builder(MainActivity.this)
               .setTitle(R.string.titleSC)
               .setCancelable(false)
               .setSingleChoiceItems(listCourse, selectedCourse, (dialog, which) -> selectedCourse = which)
                .setNegativeButton(R.string.OptionSC1, (dialog,which)->selectedCourse = beforeSelected)
                .setPositiveButton(R.string.OptionSC2, (dialog,which)->mostrarCurso(selectedCourse,listCourse))
                .create()
                .show();
    }

    private void mostrarCurso(int checkedItem,String[] listItems ){
        if(checkedItem!=-1){
            String cursoSeleccionado = listItems[checkedItem];
            ((TextView)findViewById(R.id.lbl_info2)).setText(cursoSeleccionado);
            beforeSelected = selectedCourse;
        }

    }

    private void elegirLenguaje(){

        if (checkedItems==null){
            checkedItems = new boolean[listLanguages.length];
            beforeCheckeds = new boolean[listLanguages.length];
        }
        new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.titleMC)
                .setCancelable(false)
                .setMultiChoiceItems(listLanguages,checkedItems,(dialog,which,isChecked)-> checkedItems[which] = isChecked)
                .setPositiveButton(R.string.Option2MC,(dialog,which)->mostrarLenguaje(checkedItems,listLanguages))
                .setNegativeButton(R.string.Option1MC,(dialog,which)->mostrarLenguajesAnteriores())
                .setNeutralButton(R.string.Optiom3MC,(dialog,which)->cleanLanguages())
                .create()
                .show();
    }

    private void mostrarLenguajesAnteriores() {
        checkedItems = new boolean[listLanguages.length];
        for (int i =0;i<=listLanguages.length-1;i++){
            if (beforeCheckeds[i]){
                checkedItems[i]=beforeCheckeds[i];
            }
        }
    }

    private void mostrarLenguaje(boolean[] checkedItems,String[]listItems){
        String lenguajeSeleccionado ="";
        for(int i=0;i<=listItems.length-1;i++){
            if(checkedItems[i]){
                if(lenguajeSeleccionado.isEmpty()){
                    lenguajeSeleccionado = listItems[i] + "\n";
                }else{
                    lenguajeSeleccionado += listItems[i] + "\n";
                }

            }
        }
        ((TextView)findViewById(R.id.lbl_info3)).setText(lenguajeSeleccionado);

        for (int i =0;i<=listItems.length-1;i++){
            if (checkedItems[i]){
                beforeCheckeds[i]=checkedItems[i];
            }
        }
    }




}