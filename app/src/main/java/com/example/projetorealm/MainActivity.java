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

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

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

        Produto prod3 = new Produto("Bolacha", 10, 50);
        realm.copyToRealm(prod3);

        Produto prod4 = new Produto("Geleia", 100, 500);
        realm.copyToRealm(prod4);

        Produto prod5 = new Produto("Notebook", 3, 3000);
        realm.copyToRealm(prod5);

        Produto prod6 = new Produto("Vinho", 3, 3000);
        realm.copyToRealm(prod6);

        realm.commitTransaction();
        realm.close();

        //Listando Todos
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        //RealmResults<Produto> produtos = realm.where(Produto.class).findAll();
        //RealmResults<Produto> produtos = realm.where(Produto.class).equalTo("nome", "Pipoca").findAll();

        //find first
        //Produto produto = realm.where(Produto.class).equalTo("peso", 100).findFirst();

        //filtro in
        //RealmResults<Produto> prods1 = realm.where(Produto.class).in("peso", new String[]{"Pipoca", "Notebook"} ).findAll();

        //between somente para numericos
        //RealmResults<Produto> prods1 = realm.where(Produto.class).between("peso", 10, 40 ).findAll();

        //maior que ou igual a
        //RealmResults<Produto> prods1 = realm.where(Produto.class).greaterThanOrEqualTo("peso", 50).findAll();


        /*
         * Condiçoes para Textos
         * */

        //somente para string usando contains
        //RealmResults<Produto> produtos = realm.where(Produto.class).contains("nome", "ook", Case.SENSITIVE).findAll();
        //RealmResults<Produto> produtos = realm.where(Produto.class).contains("nome", "ook", Case.SENSITIVE).findAll();

        //Begin With
        //RealmResults<Produto> produtos = realm.where(Produto.class).beginsWith("nome", "n", Case.INSENSITIVE).findAll();

        //RealmResults<Produto> produtos = realm.where(Produto.class).beginsWith("nome", "n", Case.INSENSITIVE).findAll();
        //.endsWith .like

        //RealmResults<Produto> produtos = realm.where(Produto.class).like("nome", "*nh*", Case.INSENSITIVE).findAll();
        /*RealmResults<Produto> produtos = realm.where(Produto.class)
                .equalTo("nome", "Pipoca")
                .or()
                .equalTo("preco", 3000.00)
                .findAll();*/
        /*
         * Ordenação
         * */
        RealmResults<Produto> produtos = realm.where(Produto.class).findAll();

        List<Produto> pList = new ArrayList<>();

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = new Produto(
                    produtos.get(i).getId(),
                    produtos.get(i).getNome(),
                    produtos.get(i).getPeso(),
                    produtos.get(i).getPreco()
            );

            pList.add(p);
        }


        for (int i = 0; i < pList.size(); i++) {
            Toast.makeText(
                    getApplicationContext(),
                    "Nome Primeiro Produto: " +pList.get(i).getNome() + " ID: " + pList.get(i).getId(),
                    Toast.LENGTH_LONG)
                    .show();
        }


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
