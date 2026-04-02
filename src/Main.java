import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// ================= ENUMS =================

enum StatusAssinatura {
    PENDENTE,
    AGUARDANDO_APROVACAO,
    APROVADA,
    CANCELADA
}

enum StatusCesta {
    EM_MONTAGEM,
    AGUARDANDO_APROVACAO,
    APROVADA
}

enum StatusPagamento {
    PENDENTE,
    APROVADO,
    RECUSADO
}

// ================= PRODUTOS =================

abstract class Produto {
    private String nome;

    public Produto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return nome;
    }
}

class Fruta extends Produto {
    public Fruta(String nome) {
        super(nome);
    }
}

class Legume extends Produto {
    public Legume(String nome) {
        super(nome);
    }
}

class Verdura extends Produto {
    public Verdura(String nome) {
        super(nome);
    }
}

// ================= ITEM CESTA =================

class ItemCesta {
    private Produto produto;
    private int quantidade;

    public ItemCesta(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String toString() {
        return produto.getNome() + " x" + quantidade;
    }
}

// ================= CESTA =================

class Cesta {
    private List<ItemCesta> itens = new ArrayList<>();
    private StatusCesta status = StatusCesta.EM_MONTAGEM;

    public void adicionarItem(ItemCesta item) {
        itens.add(item);
    }

    public void exibirResumo() {
        System.out.println("\nResumo da cesta:");
        for (ItemCesta item : itens) {
            System.out.println("- " + item);
        }
    }

    public void setStatus(StatusCesta status) {
        this.status = status;
    }

    public StatusCesta getStatus() {
        return status;
    }
}

// ================= PLANO =================

class Plano {
    private String nome;
    private double valor;

    public Plano(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}

// ================= ASSINANTE =================

class Assinante {
    private String nome;
    private String celular;
    private String codigo;

    public Assinante(String nome, String celular) {
        this.nome = nome;
        this.celular = celular;
    }

    public void receberCodigo(String codigo) {
        this.codigo = codigo;
        System.out.println("SMS enviado com código: " + codigo);
    }

    public boolean validarCodigo(String input) {
        return codigo.equals(input);
    }
}

// ================= ENDEREÇO =================

class EnderecoEntrega {
    private String rua;
    private String numero;
    private String cidade;

    public EnderecoEntrega(String rua, String numero, String cidade) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
    }

    public String toString() {
        return rua + ", " + numero + " - " + cidade;
    }
}

// ================= PAGAMENTO =================

class CartaoCredito {
    private String numero;

    public CartaoCredito(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }
}

class Pagamento {
    private StatusPagamento status;

    public void processar(CartaoCredito cartao) {
        if (cartao.getNumero() != null) {
            status = StatusPagamento.APROVADO;
        } else {
            status = StatusPagamento.RECUSADO;
        }
    }

    public StatusPagamento getStatus() {
        return status;
    }
}

// ================= PROTOCOLO =================

class Protocolo {
    private String numero = UUID.randomUUID().toString().substring(0, 8);

    public String getNumero() {
        return numero;
    }
}

// ================= ASSINATURA =================

class Assinatura {
    private Assinante assinante;
    private Plano plano;
    private Cesta cesta = new Cesta();
    private EnderecoEntrega endereco;
    private Pagamento pagamento;
    private Protocolo protocolo;
    private StatusAssinatura status = StatusAssinatura.PENDENTE;

    public Assinatura(Assinante assinante, Plano plano) {
        this.assinante = assinante;
        this.plano = plano;
    }

    public Cesta getCesta() {
        return cesta;
    }

    public void definirEndereco(EnderecoEntrega endereco) {
        this.endereco = endereco;
    }

    public void finalizar(CartaoCredito cartao) {
        pagamento = new Pagamento();
        pagamento.processar(cartao);

        if (pagamento.getStatus() == StatusPagamento.APROVADO) {
            status = StatusAssinatura.APROVADA;
            cesta.setStatus(StatusCesta.APROVADA);
            protocolo = new Protocolo();
        }
    }

    public void exibirResultado() {
        System.out.println("\nStatus assinatura: " + status);
        System.out.println("Status pagamento: " + pagamento.getStatus());
        System.out.println("Endereço: " + endereco);

        if (protocolo != null) {
            System.out.println("Protocolo: " + protocolo.getNumero());
        }
    }
}

// ================= MAIN =================

public class Main {
    public static void main(String[] args) {

        Assinante assinante = new Assinante("Kal-El", "11999999999");

        assinante.receberCodigo("1234");

        if (!assinante.validarCodigo("1234")) {
            System.out.println("Código inválido");
            return;
        }

        Plano plano = new Plano("Plano Médio", 59.90);
        Assinatura assinatura = new Assinatura(assinante, plano);

        assinatura.getCesta().adicionarItem(new ItemCesta(new Fruta("Maçã"), 2));
        assinatura.getCesta().adicionarItem(new ItemCesta(new Legume("Cenoura"), 1));
        assinatura.getCesta().adicionarItem(new ItemCesta(new Verdura("Alface"), 1));

        assinatura.getCesta().exibirResumo();

        EnderecoEntrega endereco = new EnderecoEntrega("Rua A", "123", "São Paulo");
        assinatura.definirEndereco(endereco);

        CartaoCredito cartao = new CartaoCredito("1111222233334444");

        assinatura.finalizar(cartao);

        assinatura.exibirResultado();
    }
}