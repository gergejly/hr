package hu.webuni.hr.gergej.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {

    private Salary salary=new Salary();

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public static class Salary{

        private Default def=new Default();
        private Smart smart = new Smart();

        public Default getDef() {
            return def;
        }

        public void setDef(Default def) {
            this.def = def;
        }

        public Smart getSmart() {
            return smart;
        }

        public void setSmart(Smart smart) {
            this.smart = smart;
        }

        public static class Default{
            private int percent;

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }
        }

        public static class Smart{
            private int percent5;
            private int percent10;
            private int percent2;

            private int limit10;
            private int limit5;
            private double limitTwoHalf;

            private TreeMap<Double, Integer> limits;

            public int getPercent5() {
                return percent5;
            }

            public void setPercent5(int percent5) {
                this.percent5 = percent5;
            }

            public int getPercent10() {
                return percent10;
            }

            public void setPercent10(int percent10) {
                this.percent10 = percent10;
            }

            public int getPercent2() {
                return percent2;
            }

            public void setPercent2(int percent2) {
                this.percent2 = percent2;
            }

            public int getLimit10() {
                return limit10;
            }

            public void setLimit10(int limit10) {
                this.limit10 = limit10;
            }

            public int getLimit5() {
                return limit5;
            }

            public void setLimit5(int limit5) {
                this.limit5 = limit5;
            }

            public TreeMap<Double, Integer> getLimits() {
                return limits;
            }

            public void setLimits(TreeMap<Double, Integer> limits) {
                this.limits = limits;
            }

            public double getLimitTwoHalf() {
                return limitTwoHalf;


            }

            public void setLimitTwoHalf(double limitTwoHalf) {
                this.limitTwoHalf = limitTwoHalf;
            }
        }

    }
}
