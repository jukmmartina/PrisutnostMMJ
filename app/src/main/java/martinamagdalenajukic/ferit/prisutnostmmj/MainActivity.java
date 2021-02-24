package martinamagdalenajukic.ferit.prisutnostmmj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RemoveClickListener, View.OnClickListener {
    private RecyclerView recycler;
    private RecyclerAdapter adapter;
    private List<String> studentList;
    private EditText etStudent;
    private Button btnDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.etStudent=(EditText) findViewById(R.id.etStudent);
        this.btnDodaj=(Button) findViewById(R.id.btnDodaj);
        setUpRecyclerData();
        setUpRecyclerView();
        this.btnDodaj.setOnClickListener(this);
    }

    private void setUpRecyclerView(){
        this.recycler=(RecyclerView) findViewById(R.id.recycler);
        this.recycler.setLayoutManager( new LinearLayoutManager(this));
        adapter=new RecyclerAdapter(this, studentList);
        this.recycler.setAdapter(adapter);
    }

    private void setUpRecyclerData(){
        studentList=new ArrayList<>();
    }

    @Override
    public void onRemoveClick(int position) {
        adapter.removeStudent(position);
    }

    @Override
    public void onClick(View v) {
        int position=studentList.size();
        if(!TextUtils.isEmpty(etStudent.getText())) {
            String student = etStudent.getText().toString();
            adapter.addNewStudent(student, position);
        }
        else Toast.makeText(this, "Insert a students name.", Toast.LENGTH_LONG).show();
    }
}