package org.example.streamingfix.repository;

import org.example.streamingfix.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepo extends JpaRepository<Library,Long> {
}
