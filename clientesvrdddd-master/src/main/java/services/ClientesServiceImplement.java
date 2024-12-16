package services;

import modelos.Clientes;

import java.util.Arrays;
import java.util.List;

public class ClientesServiceImplement implements ClientesService{

    @Override
    public List<Clientes> listar() {
        return Arrays.asList(new Clientes(1L,"Ivan","Cabrera",1125,"Masculino"),
                new Clientes(10L, "Maria", "Gonzalez", 3456, "Femenino"),
                new Clientes(15L, "Luis", "Perez", 7890, "Masculino"),
                new Clientes(20L, "Ana", "Ramirez", 1234, "Femenino"),
                new Clientes(25L, "Pedro", "Lopez", 5678, "Masculino"),
                new Clientes(30L, "Carmen", "Sanchez", 9012, "Femenino")
        );
    }
}
