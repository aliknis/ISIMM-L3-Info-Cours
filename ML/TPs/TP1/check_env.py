"""
Lab 1 - Environment check
Machine Learning - 3rd-Year Software Engineering - ISIMM

Run from a terminal, inside your activated virtual environment:
    python check_env.py

The script checks that every package used in Lab 1 can be imported,
prints the installed versions, and runs a tiny end-to-end sanity test.
It does not need an internet connection.
"""
import sys

REQUIRED = ["numpy", "pandas", "matplotlib", "sklearn"]
MIN_PYTHON = (3, 10)

ok = True
print(f"Python {sys.version.split()[0]}  ({sys.executable})")
if sys.version_info < MIN_PYTHON:
    ok = False
    print(f"  [!] Python {MIN_PYTHON[0]}.{MIN_PYTHON[1]} or newer is recommended for this course.")

for name in REQUIRED:
    try:
        module = __import__(name)
        print(f"  [ok] {name:<11} {module.__version__}")
    except ImportError:
        ok = False
        pip_name = "scikit-learn" if name == "sklearn" else name
        print(f"  [X]  {name:<11} NOT INSTALLED  ->  pip install {pip_name}")

try:
    import notebook  # noqa: F401  (classic Jupyter Notebook)
    print("  [ok] jupyter notebook available")
except ImportError:
    try:
        import jupyterlab  # noqa: F401
        print("  [ok] jupyterlab available")
    except ImportError:
        print("  [i]  Jupyter not found: install it (pip install jupyter) or open the "
              ".ipynb file in VS Code with the Python + Jupyter extensions.")

if ok:
    # Tiny sanity test: load a built-in dataset and fit a model.
    from sklearn.datasets import load_iris
    from sklearn.neighbors import KNeighborsClassifier

    X, y = load_iris(return_X_y=True)
    KNeighborsClassifier(n_neighbors=3).fit(X, y)
    print(f"\nSanity test passed: Iris loaded with shape {X.shape}, kNN fitted.")
    print("Your environment is ready for Lab 1.")
else:
    print("\nSome requirements are missing. Fix the lines marked [X] or [!] and run again.")
    sys.exit(1)
