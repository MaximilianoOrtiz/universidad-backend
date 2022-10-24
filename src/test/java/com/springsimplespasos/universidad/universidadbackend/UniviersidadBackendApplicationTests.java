package com.springsimplespasos.universidad.universidadbackend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest
class UniviersidadBackendApplicationTests {

	Calculadora calculadora = new Calculadora();

	@Test
	@DisplayName("Suma de valorA y valorB")
	void sumaDeValores() {
		//given- Armado del contexto
		int valor_a = 3;
		int valor_b = 2;

		//when - Accion que vamos a realizar, lo que queremos probar

		int expectativa = calculadora.suma(valor_a, valor_b);

		//then . Resultado que deseamos obtener, comprobar

		int resulatadoEsperado = 5;
		assertThat(expectativa).isEqualTo(resulatadoEsperado);

	}

	@Test
	@DisplayName("test deprecado")
	@Disabled("test deprecado")
	void tesDesactivados(){

	}


	class  Calculadora{
		int suma (int valor_a, int valor_b){
			return valor_a + valor_b;
		}
	}
}
