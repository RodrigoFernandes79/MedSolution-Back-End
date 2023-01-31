package med.solution.apiRest.models;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.solution.apiRest.models.enums.Especialidade;


@Table(name="medicos")
@Entity(name="Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	
	@Enumerated(EnumType.STRING)
	private Especialidade  especialidade;
	
	@Embedded /*Vamos usar Embeddable Attribute da JPA para que Endereco fique em uma classe separada,
	mas faça parte da mesma tabela de Medicos junto ao banco de dados.*/
	private Endereco enderecoCompleto;

}
