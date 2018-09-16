package br.com.luciano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class PrimaryBeansApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PrimaryBeansApplication.class, args);
		
		ImpressoraController controller = ctx.getBean(ImpressoraController.class);
		
		System.out.println("Retornando o tamanho: " + controller.fazerImpressao());
	}
}

@Controller
class ImpressoraController {
		
	private Impressora impressora;

	public ImpressoraController(Impressora impressora) {
		this.impressora = impressora;
	}
	
	public int fazerImpressao() {
		String s = "Olá, seja bem vindo!";
		
		return impressora.imprimir(s);
	}
	
}

interface Impressora {
	int imprimir(String texto);
}

@Profile("es")
@Primary
@Component
class ImpressoraHPImpl implements Impressora {

	@Override
	public int imprimir(String texto) {
		System.out.println(">>>> Imprimindo na impressora HP " + texto);
		return texto.length();
	}
	
}

@Profile("en")
@Component
class ImpressoraEpsonImpl implements Impressora {

	@Override
	public int imprimir(String texto) {
		System.out.println(">>>> Imprimindo na impressora Epson: " + texto);
		return texto.length();
	}
	
}
