import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Questao1 {

    private Musica1[] musica;
    private int i;

    public Questao1() throws FileNotFoundException, UnsupportedEncodingException {
        int totalMusicas = 200000;
        String filePath = "src/dataAEDs.csv";
        musica = new Musica1[totalMusicas];
        String entrada;

        // Composição do Array
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            entrada = br.readLine();
            i = 0;
            while (entrada != null && i < totalMusicas) {
                musica[i] = new Musica1(entrada);
                entrada = br.readLine();
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String pesquisa(String entrada) {
        String teste, res = "";
        int j;
        boolean encontrada = false;
        for (j = 0; j < i; j++) {
            teste = musica[j].getId();
            if (entrada.equals(teste)) {
                res = musica[j].imprimir();
                encontrada = true;
                break;
            }
        }
        if (!encontrada) res = "Música não encontrada";
        return res;
    }
}

class Musica1 {

    double valence;
    int year;
    double acousticness;
    String artists;
    double danceability;
    int duration_ms;
    double energy;
    String id;
    double instrumentalness;
    String key;
    double liveness;
    double loudness;
    String name;
    int popularity;

    Date release_date;
    boolean dataOk;

    double speechiness;
    double time;


    public Musica1(String entrada) {
        String[] entradaVetor = ler(entrada);
        this.setValence(Double.parseDouble(entradaVetor[0]));
        this.setYear(Integer.parseInt(entradaVetor[1]));
        this.setAcousticness(Double.parseDouble(entradaVetor[2]));
        this.setArtists(entradaVetor[3]);
        this.setDanceability(Double.parseDouble(entradaVetor[4]));
        this.setDuration_ms(Integer.parseInt(entradaVetor[5]));
        this.setEnergy(Double.parseDouble(entradaVetor[6]));
        this.setId((entradaVetor[8]));
        this.setInstrumentalness(Double.parseDouble(entradaVetor[9]));
        this.setKey(entradaVetor[10]);
        this.setLiveness(Double.parseDouble(entradaVetor[11]));
        this.setLoudness(Double.parseDouble(entradaVetor[12]));
        this.setName(entradaVetor[14]);
        this.setPopularity(Integer.parseInt(entradaVetor[15]));

        String dateString = entradaVetor[16];
        Date date;
        String[] dataVetor = dateString.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        if (dataVetor.length < 3) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.YEAR, Integer.parseInt(dataVetor[0]));
            date = calendar.getTime();
            this.setRelease_date(date);
            this.setDataOk(false);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataVetor[2]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dataVetor[1]) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(dataVetor[0]));
            date = calendar.getTime();
            this.setRelease_date(date);
            this.setDataOk(true);
        }


        this.setSpeechiness(Double.parseDouble(entradaVetor[17]));
        this.setTime(Double.parseDouble(entradaVetor[18]));

    }

    public Musica1() {
    }

    public String imprimir() {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return (this.getId() + " ## [" + this.imprimirArtistias(this.getArtists()) + "] ## " + this.getName() + " ## " +
                formatter.format(this.getRelease_date()) + " ## " + this.getAcousticness() + " ## " + this.getDanceability() +
                " ## " + this.getInstrumentalness() + " ## " + this.getLiveness() + " ## " + this.getLoudness() +
                " ## " + this.getSpeechiness() + " ## " + this.getEnergy() + " ## " + this.getDuration_ms());
    }

    public String imprimirArtistias(String entrada) {
        return entrada.trim().replaceAll("^'|'$", "").replaceAll("', '", ", ");
    }

    public static String[] ler(String entrada) {
        String[] saida = new String[20];
        int i, j = 0;
        int qtdeArrays = 0;
        boolean dentroDeAspas = false;
        String elemento = "";

        for (i = 0; i < entrada.length(); i++) {
            switch (entrada.charAt(i)) {
                case ',':
                    if (qtdeArrays == 0 && !dentroDeAspas) {
                        saida[j++] = elemento;
                        elemento = "";
                    } else {
                        elemento = elemento + entrada.charAt(i);
                    }
                    break;
                case '[':
                    if (j == 3) {
                        if (entrada.charAt(i - 1) == ',' || entrada.charAt(i - 1) == '"') qtdeArrays++;
                    } else {
                        if (!(j == 14 && (entrada.charAt(i - 2) == ',' && entrada.charAt(i - 1) == '"')))
                            elemento = elemento + entrada.charAt(i);
                    }
                    break;
                case ']':
                    if (j == 3) {
                        if (entrada.charAt(i + 1) == ',' || entrada.charAt(i + 1) == '"') qtdeArrays--;
                    } else {
                        if (!(j == 14 && (entrada.charAt(i + 2) == ',' && entrada.charAt(i + 1) == '"')))
                            elemento = elemento + entrada.charAt(i);
                    }
                    break;
                case '"':
                    if (j == 14 && (entrada.charAt(i - 1) == ',' && entrada.charAt(i + 1) == '[') || (entrada.charAt(i - 1) == ']' && entrada.charAt(i + 1) == ',')) {
                        dentroDeAspas = !dentroDeAspas;
                    } else {
                        elemento = elemento + entrada.charAt(i);
                    }
                    break;
                default:
                    elemento = elemento + entrada.charAt(i);
                    if (i == entrada.length() - 1) saida[j++] = elemento;
            }
        }

        return saida;
    }

    public boolean isDataOk() {
        return dataOk;
    }

    public void setDataOk(boolean dataOk) {
        this.dataOk = dataOk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public double getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(double acousticness) {
        this.acousticness = acousticness;
    }

    public double getDanceability() {
        return danceability;
    }

    public void setDanceability(double danceability) {
        this.danceability = danceability;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public double getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(double instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public double getValence() {
        return valence;
    }

    public void setValence(double valence) {
        this.valence = valence;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getLiveness() {
        return liveness;
    }

    public void setLiveness(double liveness) {
        this.liveness = liveness;
    }

    public double getLoudness() {
        return loudness;
    }

    public void setLoudness(double loudness) {
        this.loudness = loudness;
    }

    public double getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(double speechiness) {
        this.speechiness = speechiness;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
