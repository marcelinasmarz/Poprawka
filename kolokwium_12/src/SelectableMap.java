public class SelectableMap extends VoivodeshipMap{
    private String selectedVoivodeship;

    // Wybieramy województwo do wyróżnienia
    public void select(String voivodeship) {
        if (voivodeships.contains(voivodeship)) {
            this.selectedVoivodeship = voivodeship;
        } else {
            throw new IllegalArgumentException("Nieznane województwo: " + voivodeship);
        }
    }

    // Nadpisujemy kolor – wyróżniamy zaznaczone województwo
    @Override
    protected String getColor(String voivodeship) {
        if (voivodeship.equals(selectedVoivodeship)) {
            return "red"; // zaznaczone na czerwono
        }
        return super.getColor(voivodeship);
    }
}
