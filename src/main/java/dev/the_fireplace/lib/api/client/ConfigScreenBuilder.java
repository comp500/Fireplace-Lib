package dev.the_fireplace.lib.api.client;

import dev.the_fireplace.lib.api.chat.internal.Translator;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "WeakerAccess", "unused"})
@Environment(EnvType.CLIENT)
public abstract class ConfigScreenBuilder extends ConfigScreenBuilderBase {
    protected ConfigScreenBuilder(Translator translator) {
        super(translator);
    }

    protected ConfigCategory addStringField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        String currentValue,
        String defaultValue,
        Consumer<String> saveFunction
    ) {
        return addStringField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addStringField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        String currentValue,
        String defaultValue,
        Consumer<String> saveFunction,
        byte descriptionRowCount
    ) {
        StringFieldBuilder builder = entryBuilder.startStrField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected <T extends Enum<T>> ConfigCategory addEnumDropdown(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        T currentValue,
        T defaultValue,
        Iterable<T> dropdownEntries,
        Consumer<T> saveFunction
    ) {
        return addEnumDropdown(
            entryBuilder,
            category,
            optionTranslationBase,
            currentValue,
            defaultValue,
            dropdownEntries,
            saveFunction,
            (byte)1
        );
    }

    protected <T extends Enum<T>> ConfigCategory addEnumDropdown(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        T currentValue,
        T defaultValue,
        Iterable<T> dropdownEntries,
        Consumer<T> saveFunction,
        byte descriptionRowCount
    ) {
        List<String> stringEntries = new ArrayList<>();
        for (T entry: dropdownEntries) {
            stringEntries.add(entry.toString());
        }
        return addStringDropdown(
            entryBuilder,
            category,
            optionTranslationBase,
            currentValue.toString(),
            defaultValue.toString(),
            stringEntries,
            stringValue -> saveFunction.accept(Enum.valueOf(currentValue.getDeclaringClass(), stringValue)),
            false,
            descriptionRowCount
        );
    }

    protected ConfigCategory addStringDropdown(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        String currentValue,
        String defaultValue,
        Iterable<String> dropdownEntries,
        Consumer<String> saveFunction,
        boolean suggestionMode
    ) {
        return addStringDropdown(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, dropdownEntries, saveFunction, suggestionMode, (byte)1);
    }

    protected ConfigCategory addStringDropdown(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        String currentValue,
        String defaultValue,
        Iterable<String> dropdownEntries,
        Consumer<String> saveFunction,
        boolean suggestionMode,
        byte descriptionRowCount
    ) {
        DropdownMenuBuilder<String> builder = entryBuilder.startStringDropdownMenu(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction)
            .setSelections(dropdownEntries)
            .setSuggestionMode(suggestionMode);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addStringListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<String> currentValue,
        List<String> defaultValue,
        Consumer<List<String>> saveFunction
    ) {
        return addStringListField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addStringListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<String> currentValue,
        List<String> defaultValue,
        Consumer<List<String>> saveFunction,
        byte descriptionRowCount
    ) {
        StringListBuilder builder = entryBuilder.startStrList(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addFloatField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        float currentValue,
        float defaultValue,
        Consumer<Float> saveFunction
    ) {
        return addFloatField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    protected ConfigCategory addFloatField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        float currentValue,
        float defaultValue,
        Consumer<Float> saveFunction,
        float min,
        float max
    ) {
        return addFloatField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addFloatField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        float currentValue,
        float defaultValue,
        Consumer<Float> saveFunction,
        float min,
        float max,
        byte descriptionRowCount
    ) {
        FloatFieldBuilder builder = entryBuilder.startFloatField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setMin(min)
            .setMax(max)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addFloatSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        float currentValue,
        float defaultValue,
        Consumer<Float> saveFunction,
        float min,
        float max
    ) {
        return addFloatSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addFloatSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        float currentValue,
        float defaultValue,
        Consumer<Float> saveFunction,
        float min,
        float max,
        byte descriptionRowCount
    ) {
        LongSliderBuilder builder = entryBuilder.startLongSlider(translator.getTranslatedText(optionTranslationBase), (long) (currentValue * 1000), (long) (min * 1000), (long) (max * 1000))
            .setDefaultValue((long) (defaultValue * 1000))
            .setTextGetter(value -> Text.of(String.format("%.3f", value / 1000f)))
            .setSaveConsumer(newValue -> saveFunction.accept(newValue / 1000f));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addFloatListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Float> currentValue,
        List<Float> defaultValue,
        Consumer<List<Float>> saveFunction
    ) {
        return addFloatListField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addFloatListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Float> currentValue,
        List<Float> defaultValue,
        Consumer<List<Float>> saveFunction,
        byte descriptionRowCount
    ) {
        FloatListBuilder builder = entryBuilder.startFloatList(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addDoubleField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction
    ) {
        return addDoubleField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    protected ConfigCategory addDoubleField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction,
        double min,
        double max
    ) {
        return addDoubleField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addDoubleField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction,
        double min,
        double max,
        byte descriptionRowCount
    ) {
        DoubleFieldBuilder builder = entryBuilder.startDoubleField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setMin(min)
            .setMax(max)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addDoubleSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction,
        double min,
        double max
    ) {
        return addDoubleSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addDoubleSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction,
        double min,
        double max,
        byte descriptionRowCount
    ) {
        return addDoubleSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, descriptionRowCount, (byte)3);
    }

    protected ConfigCategory addDoubleSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction,
        double min,
        double max,
        byte descriptionRowCount,
        byte precision
    ) {
        long factor = (long) Math.pow(10, precision);
        LongSliderBuilder builder = entryBuilder.startLongSlider(translator.getTranslatedText(optionTranslationBase), (long) (currentValue * factor), (long) (min * factor), (long) (max * factor))
            .setDefaultValue((long) (defaultValue * factor))
            .setTextGetter(value -> Text.of(String.format("%." + precision + "f", value / (double)factor)))
            .setSaveConsumer(newValue -> saveFunction.accept(newValue / (double)factor));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addDoublePercentSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction
    ) {
        return addDoublePercentSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1, (byte)1);
    }

    protected ConfigCategory addDoublePercentSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        double currentValue,
        double defaultValue,
        Consumer<Double> saveFunction,
        byte descriptionRowCount,
        byte precision
    ) {
        long factor = (long) Math.pow(10, precision);
        LongSliderBuilder builder = entryBuilder.startLongSlider(translator.getTranslatedText(optionTranslationBase), (long) (currentValue * factor), 0, 100 * factor)
            .setDefaultValue((long) (defaultValue * factor))
            .setTextGetter(value -> Text.of(String.format("%." + precision + "f", value / (double)factor) + "%"))
            .setSaveConsumer(newValue -> saveFunction.accept(newValue / (double)factor));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addDoubleListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Double> currentValue,
        List<Double> defaultValue,
        Consumer<List<Double>> saveFunction
    ) {
        return addDoubleListField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addDoubleListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Double> currentValue,
        List<Double> defaultValue,
        Consumer<List<Double>> saveFunction,
        byte descriptionRowCount
    ) {
        DoubleListBuilder builder = entryBuilder.startDoubleList(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addLongField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        long currentValue,
        long defaultValue,
        Consumer<Long> saveFunction
    ) {
        return addLongField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    protected ConfigCategory addLongField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        long currentValue,
        long defaultValue,
        Consumer<Long> saveFunction,
        long min,
        long max
    ) {
        return addLongField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addLongField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        long currentValue,
        long defaultValue,
        Consumer<Long> saveFunction,
        long min,
        long max,
        byte descriptionRowCount
    ) {
        LongFieldBuilder builder = entryBuilder.startLongField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setMin(min)
            .setMax(max)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addLongSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        long currentValue,
        long defaultValue,
        Consumer<Long> saveFunction,
        long min,
        long max
    ) {
        return addLongSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addLongSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        long currentValue,
        long defaultValue,
        Consumer<Long> saveFunction,
        long min,
        long max,
        byte descriptionRowCount
    ) {
        LongSliderBuilder builder = entryBuilder.startLongSlider(translator.getTranslatedText(optionTranslationBase), currentValue, min, max)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addLongListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Long> currentValue,
        List<Long> defaultValue,
        Consumer<List<Long>> saveFunction
    ) {
        return addLongListField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addLongListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Long> currentValue,
        List<Long> defaultValue,
        Consumer<List<Long>> saveFunction,
        byte descriptionRowCount
    ) {
        LongListBuilder builder = entryBuilder.startLongList(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addIntField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        int currentValue,
        int defaultValue,
        Consumer<Integer> saveFunction
    ) {
        return addIntField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    protected ConfigCategory addIntField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        int currentValue,
        int defaultValue,
        Consumer<Integer> saveFunction,
        int min,
        int max
    ) {
        return addIntField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addIntField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        int currentValue,
        int defaultValue,
        Consumer<Integer> saveFunction,
        int min,
        int max,
        byte descriptionRowCount
    ) {
        IntFieldBuilder builder = entryBuilder.startIntField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setMin(min)
            .setMax(max)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addIntSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        int currentValue,
        int defaultValue,
        Consumer<Integer> saveFunction,
        int min,
        int max
    ) {
        return addIntSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addIntSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        int currentValue,
        int defaultValue,
        Consumer<Integer> saveFunction,
        int min,
        int max,
        byte descriptionRowCount
    ) {
        IntSliderBuilder builder = entryBuilder.startIntSlider(translator.getTranslatedText(optionTranslationBase), currentValue, min, max)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addIntListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Integer> currentValue,
        List<Integer> defaultValue,
        Consumer<List<Integer>> saveFunction
    ) {
        return addIntListField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addIntListField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        List<Integer> currentValue,
        List<Integer> defaultValue,
        Consumer<List<Integer>> saveFunction,
        byte descriptionRowCount
    ) {
        IntListBuilder builder = entryBuilder.startIntList(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addShortField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        short currentValue,
        short defaultValue,
        Consumer<Short> saveFunction
    ) {
        return addShortField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    protected ConfigCategory addShortField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        short currentValue,
        short defaultValue,
        Consumer<Short> saveFunction,
        short min,
        short max
    ) {
        return addShortField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addShortField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        short currentValue,
        short defaultValue,
        Consumer<Short> saveFunction,
        short min,
        short max,
        byte descriptionRowCount
    ) {
        IntFieldBuilder builder = entryBuilder.startIntField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setMin(min)
            .setMax(max)
            .setSaveConsumer(newValue -> saveFunction.accept(newValue.shortValue()));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addShortSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        short currentValue,
        short defaultValue,
        Consumer<Short> saveFunction,
        short min,
        short max
    ) {
        return addShortSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addShortSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        short currentValue,
        short defaultValue,
        Consumer<Short> saveFunction,
        short min,
        short max,
        byte descriptionRowCount
    ) {
        IntSliderBuilder builder = entryBuilder.startIntSlider(translator.getTranslatedText(optionTranslationBase), currentValue, min, max)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(newValue -> saveFunction.accept(newValue.shortValue()));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addByteField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        byte currentValue,
        byte defaultValue,
        Consumer<Byte> saveFunction
    ) {
        return addByteField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    protected ConfigCategory addByteField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        byte currentValue,
        byte defaultValue,
        Consumer<Byte> saveFunction,
        byte min,
        byte max
    ) {
        return addByteField(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addByteField(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        byte currentValue,
        byte defaultValue,
        Consumer<Byte> saveFunction,
        byte min,
        byte max,
        byte descriptionRowCount
    ) {
        IntFieldBuilder builder = entryBuilder.startIntField(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setMin(min)
            .setMax(max)
            .setSaveConsumer(newValue -> saveFunction.accept(newValue.byteValue()));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addByteSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        byte currentValue,
        byte defaultValue,
        Consumer<Byte> saveFunction,
        byte min,
        byte max
    ) {
        return addByteSlider(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, min, max, (byte)1);
    }

    protected ConfigCategory addByteSlider(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        byte currentValue,
        byte defaultValue,
        Consumer<Byte> saveFunction,
        byte min,
        byte max,
        byte descriptionRowCount
    ) {
        IntSliderBuilder builder = entryBuilder.startIntSlider(translator.getTranslatedText(optionTranslationBase), currentValue, min, max)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(newValue -> saveFunction.accept(newValue.byteValue()));
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }

    protected ConfigCategory addBoolToggle(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        boolean currentValue,
        boolean defaultValue,
        Consumer<Boolean> saveFunction
    ) {
        return addBoolToggle(entryBuilder, category, optionTranslationBase, currentValue, defaultValue, saveFunction, (byte)1);
    }

    protected ConfigCategory addBoolToggle(
        ConfigEntryBuilder entryBuilder,
        ConfigCategory category,
        String optionTranslationBase,
        boolean currentValue,
        boolean defaultValue,
        Consumer<Boolean> saveFunction,
        byte descriptionRowCount
    ) {
        BooleanToggleBuilder builder = entryBuilder.startBooleanToggle(translator.getTranslatedText(optionTranslationBase), currentValue)
            .setDefaultValue(defaultValue)
            .setSaveConsumer(saveFunction);
        attachDescription(optionTranslationBase, descriptionRowCount, builder);
        return category.addEntry(builder.build());
    }
}
