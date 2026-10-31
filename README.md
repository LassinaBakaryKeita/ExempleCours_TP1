# Présentation du Repository

Ce dépôt GitHub regroupe les travaux pratiques et exercices de démonstration réalisés dans le cadre du module **Spring Boot**. Il contient deux sous-projets indépendants illustrant la création d'APIs RESTful, la gestion des relations ORM avec JPA et l'intégration d'interfaces Web avec Thymeleaf.

---

## 📂 Contenu du Dépôt

### 1. 📁 [`springboot-rest-demo`](./springboot-rest-demo) — *Exercice de Démonstration du Cours*
Ce dossier contient l'application d'application pratique vue pendant le cours :
* **Sujet :** Gestion des entités `Personne` et `Adresse` via des API REST Web Services.
* **Concepts Clés :**
  * Relation bidirectionnelle `@ManyToMany` entre personnes et adresses.
  * Persistance en mémoire avec la base **H2 Database** (`jdbc:h2:mem:restdb`).
  * Gestion des cycles de sérialisation JSON/XML avec `@JsonIgnoreProperties`.
  * Support multiformat (JSON et XML via Jackson).
  * Validation des opérations CRUD (`GET`, `POST`, `PUT`, `DELETE`) via Postman et console H2.

---

### 2. 📁 [`stock de pharmacie TP1`](./stock%20de%20pharmacie%20TP1) — *TP1 : Gestion de Stock de Pharmacie*
Ce dossier contient l'application complète développée pour le **TP N°1** :
* **Sujet :** Application distribuée de gestion de stock de pharmacie.
* **Concepts Clés :**
  * Modélisation complète des relations JPA :
    * `@OneToOne` : `Produit` $\leftrightarrow$ `DetailProduit`
    * `@ManyToOne` / `@OneToMany` : `Produit` $\leftrightarrow$ `Categorie`, `Produit` $\leftrightarrow$ `Fournisseur`
    * `@ManyToMany` : `Produit` $\leftrightarrow$ `Tag` (via la table `produit_tag`)
  * Persistance de données relationnelle sous **MySQL** (`pharmacie_db`).
  * Interface graphique Web dynamique générée avec **Thymeleaf** et Bootstrap (`http://localhost:8081/produits`).
  * API REST complète pour toutes les entités de l'application (`/api/produits`, `/api/categories`, `/api/fournisseurs`, `/api/tags`).
  * Jeu de données initial injecté automatiquement au démarrage via `CommandLineRunner`.

---

