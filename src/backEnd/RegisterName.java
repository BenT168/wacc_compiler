package backEnd;

public enum  RegisterName {
    LR_REG {
        public String toString() {
            return "{lr}";
        }
    },

    PC_REG {
        public String toString() {
            return "{pc}";
        }
    },

    SP_REG {
        public String toString() {
            return "sp";
        }
    }
}
