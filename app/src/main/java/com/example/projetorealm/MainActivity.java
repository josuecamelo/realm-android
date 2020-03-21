package com.example.projetorealm;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Produto prod = new Produto();
        prod.setNome("Pipoca");
        prod.setPeso(10.5);
        prod.setPreco(120.0);
        realm.copyToRealm(prod);

        Produto prod2 = new Produto("Leite", 10, 50);
        realm.copyToRealm(prod2);

        prod = new Produto("Bolacha", 10, 50);
        realm.copyToRealm(prod);

        prod = new Produto("Geleia", 100, 500);
        realm.copyToRealm(prod);


        prod = new Produto("Notebook", 3, 3000);
        realm.copyToRealm(prod);

        realm.commitTransaction();
        realm.close();

        //Listando Todos
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        //RealmResults<Produto> produtos = realm.where(Produto.class).findAll();
        RealmResults<Produto> produtos = realm.where(Produto.class).equalTo("nome", "Pipoca").findAll();

        //find first
        //Produto produto = realm.where(Produto.class).equalTo("peso", 100).findFirst();

        //filtro in
        //RealmResults<Produto> prods1 = realm.where(Produto.class).in("peso", new String[]{"Pipoca", "Notebook"} ).findAll();

        //between somente para numericos
        //RealmResults<Produto> prods1 = realm.where(Produto.class).between("peso", 10, 40 ).findAll();

        //maior que ou igual a
        //RealmResults<Produto> prods1 = realm.where(Produto.class).greaterThanOrEqualTo("peso", 50).findAll();

        List<Produto> pList = new ArrayList<>();

        for(int i =0; i< produtos.size(); i++){
            Produto p = new Produto(
              produtos.get(i).getNome(),
              produtos.get(i).getPeso(),
              produtos.get(i).getPreco()
            );

            pList.add(p);
        }

        Toast.makeText(
                getApplicationContext(),
                "Nome Primeiro Produto: " + pList.get(0).getNome(),
                Toast.LENGTH_LONG)
                .show();

        realm.commitTransaction();
        realm.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
