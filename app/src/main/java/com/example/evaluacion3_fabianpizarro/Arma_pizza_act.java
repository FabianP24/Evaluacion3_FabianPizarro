package com.example.evaluacion3_fabianpizarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Objetos.Ingredientes;
import Objetos.Pizzas;

public class Arma_pizza_act extends AppCompatActivity {

    private Button calcular;
    private Spinner pizzas, ingredientes;
    private TextView resultado;
    private ArrayList<Pizzas> listaPizzas = new ArrayList<>();

    Ingredientes seleccion = new Ingredientes();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arma_pizza);

        calcular =      findViewById(R.id.btn_entrega);
        pizzas =        findViewById(R.id.spn_pizza);
        ingredientes =  findViewById(R.id.spn_ingrediente);
        resultado =     findViewById(R.id.tv_resultado);

        inicializarBase();

        databaseReference.child("Pizzas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot op: snapshot.getChildren()){
                    Pizzas piz = op.getValue(Pizzas.class);
                    listaPizzas.add(piz);
                    ArrayAdapter adapterPizza = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listaPizzas);
                    pizzas.setAdapter(adapterPizza);
                }
                for(DataSnapshot op: snapshot.getChildren()){
                    String pizza = op.child("nombre").getValue().toString();
                    if(pizzas.getSelectedItem().toString() == pizza){
                        String precio = op.child("precio").getValue().toString();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Ingredientes ing = new Ingredientes();
        String[] listaIngredientes = ing.getIngredientes();
        ArrayAdapter adapterIngredientes = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaIngredientes);
        ingredientes.setAdapter(adapterIngredientes);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText(seleccion.precioIngrediente(ingredientes.getSelectedItem().toString()));
            }
        });
    }

    public void inicializarBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }
}