package br.com.pedrohenrique.atividadeavaliativafase2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    TextView textViewDataConclusao;
    EditText edtDescricaoTarefa;
    Switch switchNotificacao;
    Button btnDefinirData, btnAdicionarTarefa;

    RadioGroup radioGroup;
    ListView listViewTarefas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        btnDefinirData = findViewById(R.id.btnDefinirData);
        edtDescricaoTarefa = findViewById(R.id.edtDescricaoTarefa);
        switchNotificacao = findViewById(R.id.switchNotificacao);
        btnAdicionarTarefa = findViewById(R.id.btnAdicionarTarefa);
        textViewDataConclusao = findViewById(R.id.textViewDataConclusao);
        listViewTarefas = findViewById(R.id.listViewTarefas);

        GerenciadorDB database = new GerenciadorDB(this);
        btnDefinirData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.DialogTheme , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textViewDataConclusao.setText("Data: " + day + "/" + month + "/" + year);
                    }
                }, 2024, 5, 8);

                datePickerDialog.show();
            }
        });

        btnAdicionarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(radioButtonId);
                String descricao = edtDescricaoTarefa.getText().toString();
                String prioridade = radioButton.getText().toString();
                String dataConclusao = textViewDataConclusao.getText().toString();
                Integer notificacoes = switchNotificacao.isChecked() ? 1 : 0;
                boolean checkInsert = database.insertData(descricao, prioridade, dataConclusao, notificacoes);
                if(!checkInsert) {
                    Toast.makeText(MainActivity.this, "Erro ao adicionar tarefa", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(MainActivity.this, "Tarefa Adicionada", Toast.LENGTH_SHORT).show();

                try {
                    Cursor cursor = database.getData();
                    TarefasAdapter tarefasAdapter = new TarefasAdapter(MainActivity.this, cursor);
                    listViewTarefas.setAdapter(tarefasAdapter);
                } catch (Exception error){
                    System.out.println(error.getMessage());
                }
            }
        });
    }
}