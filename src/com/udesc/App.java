package com.udesc;

import com.udesc.exception.EmployeeNotFoundException;
import com.udesc.exception.InvalidActionException;
import com.udesc.io.IO;
import com.udesc.model.Absence;
import com.udesc.model.Certificate;
import com.udesc.model.Employee;

public class App {
    public static void main(String[] args) throws Exception {
        IO.clear();

        Database.seed();

        Option option;

        do {
            IO.divider();

            Runnable runnableEmployeeNotSelected = () -> {
                IO.info("Nenhum funcionário selecionado");
            };

            State.getEmployeeOptional().ifPresentOrElse((employee) -> {
                IO.info(String.format(
                    "Funcionário selecionado: %s",
                    employee.getName()
                ));

            }, runnableEmployeeNotSelected);

            option = IO.chooseOption();

            IO.divider();

            switch (option) {
                case Option.CREATE_EMPLOYEE:
                    createEmployee();
                    break;

                case Option.UPDATE_EMPLOYEE:
                    updateEmployee();
                    break;
            
                case Option.DELETE_EMPLOYEE:
                    deleteEmployee();
                    break;

                case Option.SELECT_EMPLOYEE:
                    selectEmployee();
                    break;

                case Option.READ_EMPLOYEE:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::readEmployee, runnableEmployeeNotSelected);
                    break;

                case Option.ADD_CERTIFICATE:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::addCertificate, runnableEmployeeNotSelected);
                    break;

                case Option.REMOVE_CERTIFICATE:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::removeCertificate, runnableEmployeeNotSelected);
                    break;

                case Option.READ_CERTIFICATES:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::readCertificates, runnableEmployeeNotSelected);
                    break;

                case Option.ADD_ABSENCE:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::addAbsence, runnableEmployeeNotSelected);
                    break;

                case Option.REMOVE_ABSENCE:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::removeAbsence, runnableEmployeeNotSelected);
                    break;

                case Option.READ_ABSENCES:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::readAbsences, runnableEmployeeNotSelected);
                    break;

                case Option.TRACK_TIME:
                    State.getEmployeeOptional().ifPresentOrElse(
                        App::indicateTime, runnableEmployeeNotSelected);
                    break;

                default:
                    break;
            }

        } while (option != Option.QUIT);
    }

    private static void createEmployee() {
        IO.present(Option.CREATE_EMPLOYEE);

        try {
            Database.createEmployee(new Employee(
                IO.ask("Nome do funcionário")
            ));

            IO.info("Funcionário criado com sucesso");
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível criar o funcionário");
        }
    }

    private static void updateEmployee() {
        IO.present(Option.UPDATE_EMPLOYEE);

        try {
            final var employee = IO.chooseFromList(
                Database.getEmployees(), "Funcionários"
            );

            if (IO.confirm("Deseja alterar o nome?")) {
                employee.setName(IO.ask(String.format(
                    "Nome do funcionário (atual: %s)", employee.getName()
                )));
            }

            Database.updateEmployee(employee);

            IO.info("Funcionário atualizado com sucesso");
            IO.pause();

        } catch(EmployeeNotFoundException e) {
            IO.info(e.getMessage());

        } catch(Throwable t) {
            IO.error("Não foi possível editar o funcionário");
        }
    }

    private static void deleteEmployee() {
        IO.present(Option.DELETE_EMPLOYEE);

        try {
            final var employee = IO.chooseFromList(
                Database.getEmployees(), "Funcionários"
            );
    
            if (State.hasEmployee() && State.getEmployee().equals(employee)) {
                throw new InvalidActionException(
                    "Não você não pode remover um funcionário que está selecionado"
                );
            }

            Database.deleteEmployee(employee);

            IO.info("Funcionário removido com sucesso");
            IO.pause();

        } catch(EmployeeNotFoundException e) {
            IO.info(e.getMessage());

        } catch(InvalidActionException e) {
            IO.error(e.getMessage());

        } catch(Throwable t) {
            IO.error("Não foi possível remover o funcionário");
        }
    }

    private static void selectEmployee() {
        IO.present(Option.SELECT_EMPLOYEE);

        try {
            final var employee = IO.chooseFromList(
                Database.getEmployees(), "Funcionários"
            );

            State.setEmployee(employee);

            IO.info("Funcionário selecionado com sucesso");
            IO.pause();

        } catch(EmployeeNotFoundException e) {
            IO.info(e.getMessage());

        } catch(Throwable t) {
            IO.error("Não foi possível selecionar o funcionário");
        }
    }

    private static void readEmployee(Employee employee) {
        IO.present(Option.READ_EMPLOYEE);

        try {
            IO.view(employee);
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível ver o funcionário");
        }
    }

    private static void addCertificate(Employee employee) {
        IO.present(Option.ADD_CERTIFICATE);

        try {
            employee.getCertificates().add(new Certificate(
                IO.ask("Motivo do atestado"),
                IO.askDate("Data de início"),
                IO.askDate("Data de fim")
            ));

            IO.info("Atestado adicionado com sucesso");
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível adicionar o atestado");
        }
    }

    private static void removeCertificate(Employee employee) {
        IO.present(Option.REMOVE_CERTIFICATE);

        try {
            final var certificate = IO.chooseFromList(
                employee.getCertificates(), "Atestados"
            );

            employee.getCertificates().remove(certificate);
        
            IO.info("Atestado removido com sucesso");
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível remover o atestado");
        }
    }

    private static void readCertificates(Employee employee) {
        IO.present(Option.READ_CERTIFICATES);

        try {
            for (var certificate : employee.getCertificates()) {
                IO.view(certificate);
            }

            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível ver os atestados");
        }
    }

    private static void addAbsence(Employee employee) {
        IO.present(Option.ADD_ABSENCE);

        try {
            employee.getAbsences().add(new Absence(
                IO.ask("Motivo da falta"),
                IO.askDate("Data da falta"),
                IO.askDate("Data de retorno")
            ));

            IO.info("Falta adicionada com sucesso");
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível adicionar a falta");
        }
    }

    private static void removeAbsence(Employee employee) {
        IO.present(Option.REMOVE_ABSENCE);

        try {
            final var absence = IO.chooseFromList(
                employee.getAbsences(), "Faltas"
            );

            employee.getAbsences().remove(absence);

            IO.info("Falta removida com sucesso");
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível remover a falta");
        }
    }

    private static void readAbsences(Employee employee) {
        IO.present(Option.READ_ABSENCES);

        try {
            for (var absence : employee.getAbsences()) {
                IO.view(absence);
            }

            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível ver as faltas");
        }
    }

    private static void indicateTime(Employee employee) {
        IO.present(Option.TRACK_TIME);

        try {
            employee.getTimeTracking().add(
                IO.askTime("Apontamento")
            );

            IO.info("Apontamento realizado com sucesso");
            IO.pause();

        } catch(Throwable t) {
            IO.error("Não foi possível realizar o apontamento");
        }
    }
}
