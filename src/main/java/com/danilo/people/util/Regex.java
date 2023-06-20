package com.danilo.people.util;

import org.springframework.stereotype.Component;

@Component
public class Regex {
    public boolean isValidCPF(String cpf) {
        // Remove os caracteres especiais da string CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se a string CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula os dígitos verificadores do CPF
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }

        int firstDigit = (sum * 10) % 11;
        if (firstDigit == 10) {
            firstDigit = 0;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }

        int secondDigit = (sum * 10) % 11;
        if (secondDigit == 10) {
            secondDigit = 0;
        }

        // Verifica se os dígitos verificadores calculados correspondem aos dígitos informados
        return (digits[9] == firstDigit && digits[10] == secondDigit);
    }
}
