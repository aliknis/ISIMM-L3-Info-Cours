# Lab 1 — First Contact with Data (Machine Learning, ISIMM)

3rd-Year Software Engineering · Chapter 1 · Instructor: Professor Nizar Omheni · 90 min · pairs

## Files

| File | What it is |
|---|---|
| `Lab01_Student_Handout.pdf` | The lab subject: scenario, tasks, hints, deliverables. Read it first. |
| `lab01_starter.ipynb` | The notebook you complete and submit. |
| `check_env.py` | Checks your Python environment (Task 1). |
| `buggy_pipeline.py` | A teammate's script to review (Task 7). Do not "fix" it in place — write the fix in the notebook. |
| `requirements.txt` | Packages to install. |

Keep all files **in the same folder**: the notebook runs `buggy_pipeline.py` from its own directory.

## Setup (≈ 5 min, do it before the session if you can)

```bash
python -m venv ml-env
# Linux/macOS:  source ml-env/bin/activate
# Windows:      ml-env\Scripts\activate
pip install -r requirements.txt
python check_env.py
jupyter notebook lab01_starter.ipynb
```

No dataset download is needed: Iris and Wine ship with scikit-learn. Internet is only needed to install packages.
No GPU is needed; everything runs in seconds on any laptop.

**If installation fails in the lab room:** open `lab01_starter.ipynb` in VS Code (Python + Jupyter extensions) with any Python that has
the four packages, or work with a classmate's machine and swap at Task 5. Tell the instructor early — do not lose the session on setup.

## Submission

Rename the notebook `lab01_<name1>_<name2>.ipynb`, run *Kernel → Restart & Run All*, save, and submit it through the channel your
instructor indicates. All ✍️ answers are written inside the notebook.
