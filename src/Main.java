/*
 * Lidiano de Jesus Santos
 * Projeto de Bloco: Desenvolvimento Back-End [26E4_5] - TP1
 * Sistema de Gestão de Biblioteca Universitária - programa standalone inicial
 * (sem orientação a objetos: só tipos primitivos, operadores e controle de fluxo)
 */
import java.util.Scanner;

public class Main {

    static final int PRAZO_DIAS = 14;          // RN01: prazo padrão de 14 dias corridos
    static final int LIMITE_EMPRESTIMOS = 3;   // RN03: no máximo 3 empréstimos ativos

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("=== Biblioteca Universitária ===");
            System.out.println("1 - Verificar limite de empréstimos");
            System.out.println("2 - Calcular prazo de devolução");
            System.out.println("3 - Calcular multa por atraso");
            System.out.println("0 - Sair");
            opcao = lerInteiro(entrada, "Opção: ");

            switch (opcao) {
                case 1:
                    verificarLimite(entrada);
                    break;
                case 2:
                    calcularPrazo(entrada);
                    break;
                case 3:
                    calcularMulta(entrada);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida, escolha 0, 1, 2 ou 3.");
            }
        }
        entrada.close();
    }

    // Opção 1 - RN03: cada membro pode ter no máximo 3 empréstimos ativos
    static void verificarLimite(Scanner entrada) {
        int ativos = lerInteiro(entrada, "Empréstimos ativos do membro: ");

        if (ativos < 0) {
            System.out.println("Valor inválido: a quantidade não pode ser negativa.");
        } else if (ativos > LIMITE_EMPRESTIMOS) {
            System.out.println("Quantidade acima do limite permitido (" + LIMITE_EMPRESTIMOS
                    + "). Situação inconsistente: verifique o cadastro do membro.");
        } else if (ativos == LIMITE_EMPRESTIMOS) {
            System.out.println("Limite de " + LIMITE_EMPRESTIMOS
                    + " empréstimos atingido. Novo empréstimo não permitido.");
        } else {
            int restantes = LIMITE_EMPRESTIMOS - ativos;
            System.out.println("Pode realizar empréstimo. Restam " + restantes + " empréstimo(s).");
        }
    }

    // Opção 2 - RN01: prazo = data de retirada + 14 dias corridos
    static void calcularPrazo(Scanner entrada) {
        int dia = lerInteiro(entrada, "Dia da retirada: ");
        int mes = lerInteiro(entrada, "Mês da retirada: ");

        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido: informe um valor de 1 a 12.");
            return;
        }
        int diasNoMes = diasDoMes(mes);
        if (dia < 1 || dia > diasNoMes) {
            System.out.println("Dia inválido: o mês " + mes + " tem " + diasNoMes + " dias.");
            return;
        }

        int diaPrazo = dia + PRAZO_DIAS;
        int mesPrazo = mes;
        if (diaPrazo > diasNoMes) {      // virada de mês
            diaPrazo = diaPrazo - diasNoMes;
            mesPrazo = mesPrazo + 1;
            if (mesPrazo > 12) {         // virada de ano
                mesPrazo = 1;
            }
        }

        System.out.printf("Retirada: %02d/%02d  ->  Prazo de devolução: %02d/%02d%n",
                dia, mes, diaPrazo, mesPrazo);
    }

    // Suposição S01/TP1: ano não bissexto (fevereiro com 28 dias)
    static int diasDoMes(int mes) {
        switch (mes) {
            case 2:
                return 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    // Opção 3 - RN02: multa = dias corridos de atraso x valor diário
    static void calcularMulta(Scanner entrada) {
        int diasAtraso = lerInteiro(entrada, "Dias de atraso: ");
        if (diasAtraso < 0) {
            System.out.println("Valor inválido: os dias de atraso não podem ser negativos.");
            return;
        }
        double valorDia = lerDecimal(entrada, "Valor da multa por dia (R$): ");
        if (valorDia < 0) {
            System.out.println("Valor inválido: o valor diário não pode ser negativo.");
            return;
        }

        if (diasAtraso == 0) {
            System.out.println("Devolução dentro do prazo: sem multa.");
        } else {
            double total = diasAtraso * valorDia;
            System.out.printf("Multa: %d dia(s) x R$ %.2f = R$ %.2f%n", diasAtraso, valorDia, total);
        }
    }

    // Lê um número inteiro; se o usuário digitar texto, pede de novo (RNF01)
    static int lerInteiro(Scanner entrada, String mensagem) {
        System.out.print(mensagem);
        while (!entrada.hasNextInt()) {
            System.out.print("Digite um número inteiro: ");
            entrada.next();              // descarta o que não é número
        }
        return entrada.nextInt();
    }

    // Lê um número decimal aceitando vírgula ou ponto (1,50 ou 1.50); se não for número, pede de novo
    static double lerDecimal(Scanner entrada, String mensagem) {
        System.out.print(mensagem);
        String texto = entrada.next().replace(',', '.');
        while (!texto.matches("-?\\d+(\\.\\d+)?")) {
            System.out.print("Digite um valor numérico (ex.: 1,50): ");
            texto = entrada.next().replace(',', '.');
        }
        return Double.parseDouble(texto);
    }
}