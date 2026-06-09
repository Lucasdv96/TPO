package Clases;

/**
 * Representa un stream activo (sesión de reproducción) como si fuera una JAM de spotify ->
 * -> que simula como un live donde varios usuarios escuchan temas y uno tiene el control .
 * Almacenado en el Diccionario: streamId -> Stream.
 * El streamId es la clave; el objeto Stream es el valor.
 * Aca es lo que les decia antes que tambien se puede "simular"
 * la reproduccion
 *
 */
public class Stream {

    public enum Estado { ACTIVO, PAUSADO, FINALIZADO }

    private String streamId;     // clave en el Diccionario
    private int idUsuario;       // referencia al usuario dueño de ese stream
    private int idCancion;       // canción que se está reproduciendo
    private Estado estado;
    private long timestamp;      // System.currentTimeMillis() esto es al momento de crear, es lo que hablamos que es como el "DATE" de SQL

    public Stream(String streamId, int idUsuario, int idCancion) {
        this.streamId = streamId;
        this.idUsuario = idUsuario;
        this.idCancion = idCancion;
        this.estado = Estado.ACTIVO;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("Stream[%s] usuario:%d cancion:%d estado:%s", // el "%" es para printear lindo nomas
                streamId, idUsuario, idCancion, estado);
    }

    public String getStreamId()     { return streamId; }
    public int getIdUsuario()       { return idUsuario; }
    public int getIdCancion()       { return idCancion; }
    public Estado getEstado()       { return estado; }
    public void setEstado(Estado e) { this.estado = e; }
    public long getTimestamp()      { return timestamp; }
}