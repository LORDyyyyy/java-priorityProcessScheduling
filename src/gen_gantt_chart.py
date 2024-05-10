import os
import matplotlib.pyplot as plt

def generate_gantt_chart():
    num_processes = int(input())
    arrival_times = list(map(int, input().split()))
    burst_times = list(map(int, input().split()))
    priorities = list(map(int, input().split()))

    processes = [f"P{i+1}" for i in range(num_processes)]

    completion_time = [0] * num_processes
    waiting_time = [0] * num_processes
    turnaround_time = [0] * num_processes
    remaining_time = burst_times.copy()
    total_completion_time = 0
    current_time = 0
    gantt_chart = []

    while True:
        available_processes = [(i, burst_times[i], priorities[i]) for i in range(num_processes) if
                                arrival_times[i] <= current_time and remaining_time[i] > 0]

        if not available_processes:
            break

        available_processes.sort(key=lambda x: (x[2], x[0]))

        selected_process = available_processes[0]
        process_index = selected_process[0]
        burst_time = selected_process[1]

        gantt_chart.append((current_time, current_time + burst_time, process_index))

        current_time += burst_time
        remaining_time[process_index] = 0

        completion_time[process_index] = current_time
        turnaround_time[process_index] = completion_time[process_index] - arrival_times[process_index]
        waiting_time[process_index] = turnaround_time[process_index] - burst_times[process_index]

    fig, gnt = plt.subplots()
    for i in range(len(gantt_chart)):
        colors = ['tab:blue', 'tab:orange', 'tab:green', 'tab:red', 'tab:purple', 'tab:brown', 'tab:pink', 'tab:gray',
                    'tab:olive', 'tab:cyan']
        process_index = gantt_chart[i][2]
        gnt.broken_barh([(gantt_chart[i][0], gantt_chart[i][1] - gantt_chart[i][0])], (process_index, 1),
                        facecolors=(colors[process_index % 10]))
    gnt.set_xlabel('Seconds since start')
    gnt.set_xlim(0, current_time)
    gnt.set_xticks(range(0, current_time + 1, 3))
    gnt.set_ylabel('Processes number')
    gnt.set_yticks(range(num_processes))
    gnt.set_yticklabels(processes)
    plt.title('Gantt Chart for Non-Preemptive Priority Scheduling')
    plt.grid(True)

    script_dir = os.path.dirname(os.getcwd())
    chart_path = os.path.join(script_dir, 'gantt_chart.png')
    plt.savefig(chart_path)
    plt.close()

    print(chart_path)


generate_gantt_chart()