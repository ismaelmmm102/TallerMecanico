import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$";
    private static final String ER_DNI = "(\\d{8})([A-Z])";
    private static final String ER_TELEFONO = "(\\d{9})";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono){
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }
    public Cliente(Cliente cliente){
        this.nombre = cliente.getNombre();
        this.dni = cliente.getDni();
        this.telefono = cliente.getTelefono();
    }
    private static boolean letraDniValida(String dni) {
        String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        int resto = numero % 23;
        char letra = letrasValidas.charAt(resto);
        return letra == dni.charAt(8);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.matches(ER_NOMBRE)){
            throw new IllegalArgumentException("Formato o nombre nulo");
        }
        this.nombre=nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (!dni.matches(ER_DNI) && !letraDniValida(dni)){
            throw new IllegalArgumentException("El formato del dni es incorrecto");
        }
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("El telefono es invalido.");
        }
        this.telefono = telefono;
    }
    public static Cliente get (String dni){
        return null;
    }
}
