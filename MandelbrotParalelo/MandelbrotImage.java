package MandelbrotParalelo;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class MandelbrotImage {
    private final int width;
    private final int height;
    private final int maxIter;

    //Nosso construtor paralelo
    public MandelbrotImage(int width, int height, int maxIter) {
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }

    public void generateImage(String fileName, int numThreads) throws IOException {
        //Cria um objeto para armazenamento da imagem
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //Delimita o numero de threads passada por parametro
        //Usando mais threads do que seu computador possui fisicamente, o pool cria um número maior de threads lógicas.
        ForkJoinPool pool = new ForkJoinPool(numThreads);

        try {
            //Cria o objeto usado na task (mesmo objeto anterior) sem roda-lo
            MandelbrotTask task = new MandelbrotTask(0, height, image, width, height, maxIter);
            //Captura o tempo inicial da execução
            long startTime = System.nanoTime();
            //Começa a rotina do calculo
            pool.invoke(task);
            //Captura o tempo final da execução
            long endTime = System.nanoTime();
            //Calcula o tempo e printa no console
            long duration = (endTime - startTime) / 1_000_000; 
            System.out.println("Tempo de execução (Paralela com, " + numThreads + " threads): " + duration / 1000.0 + " segundos");
            ImageIO.write(image, "png", new File(fileName));
        } finally {
            pool.shutdown();
        }
    }
}


