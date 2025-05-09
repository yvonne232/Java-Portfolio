import matplotlib.pyplot as plt
import os
import pandas as pd
import re

# Parse the sort stats
def parse_stats_from_file(file_path):
    stats = {
        "Sort": None,
        "File": None,
        "Comparisons": None,
        "Exchanges": None,
        "Execution Time": None,
        "Input Type": None,
        "Size": None
    }

    with open(file_path, 'r') as f:
        for line in f:
            if line.startswith("Sort"):
                stats["Sort"] = line.split("Sort:")[-1].strip()
            elif line.startswith("File:"):
                stats["File"] = line.split("File:")[-1].strip()
                match = re.match(r"(asc|rev|dup|ran)(\d+)(K?)", stats["File"])
                if match:
                    input_type = match.group(1)
                    stats["Input Type"] = {
                        "asc": "Ascending Order",
                        "rev": "Reverse Order",
                        "dup": "Random Order with Duplicates",
                        "ran": "Random Order"
                    }.get(input_type, "Unknown")
                    stats["Size"] = int(match.group(2)) * (1000 if match.group(3) == "K" else 1)
            elif "Comparisons:" in line and "Exchanges:" in line:
                comps = re.search(r"Comparisons:\s*(\d+)", line)
                exchanges = re.search(r"Exchanges:\s*(\d+)", line)
                if comps:
                    stats["Comparisons"] = int(comps.group(1))
                if exchanges:
                    stats["Exchanges"] = int(exchanges.group(1))
            elif line.startswith("Execution Time:"):
                time_val = line.split("Execution Time:")[-1].strip()
                stats["Execution Time"] = int(re.search(r"\d+", time_val).group())
    return stats

# Turn parsed stats into one dataframe
# Turn parsed stats into one dataframe
def collect_all_stats(folder):
    all_stats = []
    for root, _, files in os.walk(folder):
        for f in files:
            if f.endswith('.dat'):
                result = parse_stats_from_file(os.path.join(root, f))
                if result:
                    all_stats.append(result)
    return pd.DataFrame(all_stats)

# plot function
def plot_stats(df):
    for metric in ["Comparisons", "Exchanges", "Execution Time"]:
        plt.figure(figsize=(15, 8))
        for input_type in df["Input Type"].unique():
            subset = df[df["Input Type"] == input_type]
            subset = subset.sort_values("Size")
            for sort_method in subset["Sort"].unique():
                subsub = subset[subset["Sort"] == sort_method]
                plt.plot(subsub["Size"], subsub[metric], marker='o', label=f"{sort_method} ({input_type})")

        plt.title(f"{metric} vs. Input Size")
        plt.xlabel("Input Size")
        plt.ylabel(metric)
        plt.legend(fontsize=8, loc='upper left', bbox_to_anchor=(1, 1))
        plt.tight_layout(rect=[0, 0, 0.95, 1])
        plt.grid(True)
        plt.show()

    for metric in ["Comparisons", "Exchanges", "Execution Time"]:
        for input_type in df["Input Type"].unique():
            plt.figure(figsize=(12, 6))
            subset = df[df["Input Type"] == input_type].sort_values("Size")

            for sort_method in subset["Sort"].unique():
                subsub = subset[subset["Sort"] == sort_method]
                plt.plot(subsub["Size"], subsub[metric], marker='o', label=sort_method)

            plt.title(f"{metric} vs. Input Size ({input_type})")
            plt.xlabel("Input Size")
            plt.ylabel(metric)
            plt.legend()
            plt.grid(True)
            plt.tight_layout()
            plt.show()

folder_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', 'Output', 'Required Input_Output'))
all_stats = collect_all_stats(folder_path)

# Sort the table
sort_order = ["QuickSortA", "QuickSortB", "QuickSortC", "QuickSortD", "MergeSort"]
all_stats["Sort"] = pd.Categorical(all_stats["Sort"], categories=sort_order, ordered=True)
all_stats = all_stats.sort_values(["Size","Sort"]).reset_index(drop=True)
# Save the stats to csv
all_stats.to_csv(os.path.join(folder_path, 'summary.csv'), index = False)

plot_stats(all_stats)
