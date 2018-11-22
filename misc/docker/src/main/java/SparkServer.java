import static spark.Spark.*;

public class SparkServer {

    public static void main(String[] args) {
        var helloWorld = "Hello World!";

        port(8080);
        ipAddress("0.0.0.0");
        redirect.any("/", "/hello");
        get("/hello", (req, res) -> helloWorld);
    }
}
