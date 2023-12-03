package com.bookatop.country.world;

public class Country {

    private String name;

    private String code;

    private String capital;

    private String region;

    private String demonym;

    private CountryCurrency currency;

    private CountryLanguage language;

    private String flag;

    private String dialling_code;

    private String isoCode;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public CountryCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(CountryCurrency currency) {
        this.currency = currency;
    }

    public CountryLanguage getLanguage() {
        return language;
    }

    public void setLanguage(CountryLanguage language) {
        this.language = language;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDialling_code() {
        return dialling_code;
    }

    public void setDialling_code(String dialling_code) {
        this.dialling_code = dialling_code;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }
}
