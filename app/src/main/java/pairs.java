public class pairs<F, S> {


        public final F first;
        public final S second;

        /**
         * Constructor for a Pair.
         *
         * @param first the first object in the Pair
         * @param second the second object in the pair
         */
        public pairs(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public S getvalue(){
            return this.second;
        }
}
