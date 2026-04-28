# Recipe and Ingredient Knowledge Graph

## Overview
This project implements a knowledge graph for recipes, ingredients, and tags. It allows users to query what they can cook, find similar recipes, and explore ingredient substitutions.

## Features
- Find recipes based on available ingredients
- BFS traversal for cookable recipes
- Jaccard similarity between recipes
- Filter recipes by tags
- Ingredient substitution support
- Meal planner (bonus feature)

## Data Structures
- HashMap for fast lookup (O(1))
- Set for unique ingredients
- Graph represented using adjacency relationships

## Algorithms
- Breadth-First Search (BFS)
- Jaccard Similarity

## Complexity
- BFS: O(V + E)
- Jaccard: O(n + m)
- HashMap: O(1)

## Demo (CLI)
Example commands:
