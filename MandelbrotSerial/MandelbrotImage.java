package MandelbrotSerial;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MandelbrotImage {
    private final int width;
    private final int height;
    private final int maxIter;

    //Nosso construtor serial
    public MandelbrotImage(int width, int height, int maxIter) {
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }


    public void generateImage(String fileName) throws IOException {
        //Cria um objeto para armazenamento da imagem
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //Captura o tempo inicial da execução
        long startTime = System.nanoTime();
        
        //Aplicação da fórmula de Mandelbrot
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - width / 2) * 4.0 / width;
                double cY = (y - height / 2) * 4.0 / width;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                image.setRGB(x, y, iter | (iter << 8));
            }
        }
        //Captura o tempo final da execução
        long endTime = System.nanoTime();
        //Calcula o tempo e printa no console
        long duration = (endTime - startTime) / 1_000_000; 
        System.out.println("Tempo de execução (serial): " + duration / 1000.0 + " segundos");
        // System.out.println("Tempo de execução (serial): " + duration + "ms");
        ImageIO.write(image, "png", new File(fileName));
    }
}