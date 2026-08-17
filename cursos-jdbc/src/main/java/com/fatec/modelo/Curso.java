package com.fatec.modelo;

public class Curso {

    private int id;
    private String nome;
    private int cargaHoraria;
    private String professor;

    public Curso() {
    }

    public Curso(String nome, int cargaHoraria, String professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    public Curso(int id, String nome, int cargaHoraria, String professor) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Carga Horária: " + cargaHoraria + "h | Professor: " + professor;
    }
}