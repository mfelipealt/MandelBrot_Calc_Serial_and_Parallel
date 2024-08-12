package MandelbrotSerial;

import java.io.IOException;

public class MandelbrotSet {
    public static void main(String[] args) {
        int width = 8000; //Largura da imagem
        int height =8000; //Altura da imagem
        int maxIter = 3500; //Número máximo de iterações para determinar se um ponto pertence ao conjunto de Mandelbrot
        String fileName = "mandelbrot_1_serial.png"; //Nome do arquivo gerado (imagem)

        MandelbrotImage mandelbrotImage = new MandelbrotImage(width, height, maxIter); //Cria o objeto

        try {
            mandelbrotImage.generateImage(fileName); //Inicia a rotina com base no objeto criado
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
