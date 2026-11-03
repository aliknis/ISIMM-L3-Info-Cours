"""
Lab 1 - Task 7: Code review
Machine Learning - 3rd-Year Software Engineering - ISIMM

SCENARIO (hypothetical): a teammate wrote this script and posted in the team
channel: "kNN is perfect on Iris: 100% accuracy! And even on a held-out split
it beats the baseline by more than 60 points."

Your job: run it, read it carefully, and find what is wrong with the
EVALUATION. Do not change the model. Write your diagnosis in your notebook,
then write a corrected version.

    python buggy_pipeline.py
"""
import numpy as np
from sklearn.datasets import load_iris
from sklearn.dummy import DummyClassifier
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier

X, y = load_iris(return_X_y=True)

# ---------------- Report A ----------------
model = KNeighborsClassifier(n_neighbors=1)
model.fit(X, y)
acc_a = model.score(X, y)
print(f"Report A - kNN (k=1) accuracy: {acc_a:.3f}")

# ---------------- Report B ----------------
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, shuffle=False
)

baseline = DummyClassifier(strategy="most_frequent").fit(X_train, y_train)
knn = KNeighborsClassifier(n_neighbors=3).fit(X_train, y_train)

acc_base = baseline.score(X_test, y_test)
acc_knn = knn.score(X_test, y_test)
print(f"Report B - baseline accuracy: {acc_base:.3f}")
print(f"Report B - kNN (k=3) accuracy: {acc_knn:.3f}")
print(f"Report B - improvement over baseline: {100 * (acc_knn - acc_base):.1f} points")

# Hint for the reviewer (uncomment when you are ready):
# print("train class counts:", np.bincount(y_train, minlength=3))
# print("test  class counts:", np.bincount(y_test, minlength=3))
