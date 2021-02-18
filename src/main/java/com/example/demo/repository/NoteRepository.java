package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Note;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer>{
	public List<Note> findByLabelContainingOrMessageContaining(String label, String msg);
}
