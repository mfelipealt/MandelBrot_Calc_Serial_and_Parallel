package MandelbrotParalelo;

import java.io.IOException;

public class MandelbrotSet {
    public static void main(String[] args) {
        int width = 8000; //Largura da imagem
        int height = 8000; //Altura da imagem
        int maxIter = 3500; //Número máximo de iterações para determinar se um ponto pertence ao conjunto de Mandelbrot
        String fileName = "mandelbrot_1_paralelo.png"; //Nome do arquivo gerado (imagem)

        MandelbrotImage mandelbrotImage = new MandelbrotImage(width, height, maxIter);

        int[] threadCounts = {1, 2, 4, 6, 8, 16}; //Define as threads utilizadas em cada iteração 

        for (int numThreads : threadCounts) {
            try {
                mandelbrotImage.generateImage(fileName, numThreads); //Inicia a rotina com base no objeto criado e nas threads utilizadas
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


