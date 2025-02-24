package fr.eql.ai116.linus.wattelse.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class BankAccountDto implements Serializable {


        private Long idBankAccount;
        private Long userId;
        private LocalDate registerDate;
        private LocalDate unRegisterDate;
        private String iban;
        private String bic;

        /// Constructeur vide
        public BankAccountDto() {
        }

        /// Constructeur surchargé
        public BankAccountDto(Long idBankAccount, Long userId, LocalDate registerDate,
                           LocalDate unRegisterDate, String iban, String bic) {
            this.idBankAccount = idBankAccount;
            this.userId = userId;
            this.registerDate = registerDate;
            this.unRegisterDate = unRegisterDate;
            this.iban = iban;
            this.bic = bic;
        }

    public BankAccountDto(String iban, String bic) {
        this.iban = iban;
        this.bic = bic;
    }

    /// Getters
        public Long getIdBankAccount() {
            return idBankAccount;
        }

        public Long getUserId() {
            return userId;
        }

        public LocalDate getRegisterDate() {
            return registerDate;
        }

        public LocalDate getUnRegisterDate() {
            return unRegisterDate;
        }

        public String getIban() {
            return iban;
        }

        public String getBic() {
            return bic;
        }

        /// Setters
        public void setIdBankAccount(Long idBankAccount) {
            this.idBankAccount = idBankAccount;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public void setRegisterDate(LocalDate registerDate) {
            this.registerDate = registerDate;
        }

        public void setUnRegisterDate(LocalDate unRegisterDate) {
            this.unRegisterDate = unRegisterDate;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public void setBic(String bic) {
            this.bic = bic;
        }

        /// Méthodes
        @Override
        public String toString() {
            return "BankAccount{" +
                    "idBankAccount=" + idBankAccount +
                    ", userId=" + userId +
                    ", registerDate=" + registerDate +
                    ", unRegisterDate=" + unRegisterDate +
                    ", iban=" + iban +
                    ", bic=" + bic +
                    '}';
        }
    }

