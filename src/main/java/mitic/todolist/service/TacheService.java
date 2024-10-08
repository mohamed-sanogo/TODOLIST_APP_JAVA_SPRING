package mitic.todolist.service;

import mitic.todolist.entite.Tache;
import mitic.todolist.repository.TacheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    private TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public Tache createTache(Tache tache) {
        if (tache.getId() == null) {
            Optional<Tache> verifyTache = tacheRepository.findByTitle(tache.getTitle());
            if (verifyTache.isPresent()) {
                throw new IllegalArgumentException("Une tâche avec ce titre existe déjà.");
            }
        } else {
            Optional<Tache> verifyTache = tacheRepository.findByTitle(tache.getTitle());
            if (verifyTache.isPresent() && !verifyTache.get().getId().equals(tache.getId())) {
                throw new IllegalArgumentException("Une autre tâche avec ce titre existe déjà.");
            }
        }

        return tacheRepository.save(tache);
    }

    public Optional<Tache> findById(int id) {
        return tacheRepository.findById(id);
    }
    public List<Tache> getTache(){
        return tacheRepository.findAll();
    }

    public void deleteById(int id) {
        tacheRepository.deleteById(id);
    }
}
