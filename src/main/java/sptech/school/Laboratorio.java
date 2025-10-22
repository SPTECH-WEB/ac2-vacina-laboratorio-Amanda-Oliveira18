package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.VacinaInvalidaException;
import school.sptech.exception.VacinaNaoEncontradaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio {
  private String nome;
  private List<Vacina> vacinas;

  public Laboratorio() {
    this.nome = nome;
    this.vacinas = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Vacina> getVacinas() {
    return vacinas;
  }

  public void adicionarVacina(Vacina vacina) throws VacinaInvalidaException {
    LocalDate dataAtual = LocalDate.now();
    if(vacina == null){
      throw new VacinaInvalidaException("A vacina não pode ser nula");
    }
    if(vacina.getCodigo() == null || vacina.getCodigo().isEmpty()){
      throw new VacinaInvalidaException("O código da vacina não pode ser nulo / vazio");
    }
    if(vacina.getNome() == null || vacina.getNome() == ""){
      throw new VacinaInvalidaException("O nome da vacina não pode ser nulo / vazio");
    }
    if(vacina.getTipo() == null || vacina.getTipo() == ""){
      throw new VacinaInvalidaException("O tipo da vacina não pode ser nulo / vazio");
    }
    if(vacina.getPreco() == null || vacina.getPreco() <= 0){
      throw new VacinaInvalidaException("O preço da vacina não pode ser nulo / menor que 0");
    }
    if(vacina.getEficacia() == null || vacina.getEficacia() < 0 || vacina.getEficacia() > 100){
      throw new VacinaInvalidaException("Eficácia inválida!");
    }
    if(vacina.getDataLancamento() == null || dataAtual.isBefore(vacina.getDataLancamento())){
      throw new VacinaInvalidaException("Data de lançamento inválida / nula!");
    }
    vacinas.add(vacina);

  }

  public Vacina buscarVacinaPorCodigo(String codigo) throws ArgumentoInvalidoException, VacinaNaoEncontradaException {
    Vacina vacinaEncontrada = null;
    if(codigo == null || codigo.isEmpty() || codigo.isBlank()){
      throw new ArgumentoInvalidoException("O código não é válido!");
    }
    for(Vacina vacina : vacinas){
      if(vacina.getCodigo().equals(codigo)){
        vacinaEncontrada = vacina;
        return vacinaEncontrada;
      }
    }
    throw new VacinaNaoEncontradaException("Vacina não encontrada!");
  }

  public void removerVacinaPorCodigo(String codigo){
    Vacina vacinaEncontrada = null;
    if(codigo == null || codigo.isEmpty() || codigo.isBlank()){
      throw new ArgumentoInvalidoException("O código não é válido!");
    }
    for(Vacina vacina : vacinas){
      if(vacina.getCodigo().equals(codigo)){
        vacinas.remove(vacina);
        return;
      }
    }
    throw new VacinaNaoEncontradaException("Vacina não encontrada!");
  }

  public Vacina buscarVacinaComMelhorEficacia() throws VacinaNaoEncontradaException{
    Vacina vacinaComMelhorEficacia = null;
    Double eficaciaMelhor = 0.0;
    if(vacinas.isEmpty()){
      throw new VacinaNaoEncontradaException("Vacina não encontrada!");
    }
    for(Vacina vacina : vacinas){
      if(vacina.getEficacia() > eficaciaMelhor){
        eficaciaMelhor = vacina.getEficacia();
        vacinaComMelhorEficacia = vacina;
      }
    }
    return vacinaComMelhorEficacia;
  }

  public List<Vacina> buscarVacinaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws ArgumentoInvalidoException{
    List<Vacina> vacinasDoPeriodo = new ArrayList<>();
    if (dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)){
      throw new ArgumentoInvalidoException("As datas são inválidas!");
    }
    for(Vacina vacina : vacinas){
      if(vacina.getDataLancamento().isAfter(dataInicio) && vacina.getDataLancamento().isBefore(dataFim)){
        vacinasDoPeriodo.add(vacina);
      }
    }
    return vacinasDoPeriodo;
  }
}
