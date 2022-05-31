package design.listenter.demo4;

class TaskThread extends Thread {
        private Future<Long> future;
        private int start;
        private int end;

        public TaskThread(Future<Long> future, int start, int end) {
            this.future = future;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += i;
            }
            future.complete(sum);
        }
    }