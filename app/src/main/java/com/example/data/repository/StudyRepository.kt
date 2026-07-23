package com.example.data.repository

import com.example.data.local.BookmarkedQuestion
import com.example.data.local.QuizAttempt
import com.example.data.local.StudyDao
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow

class StudyRepository(private val studyDao: StudyDao) {

    val allAttempts: Flow<List<QuizAttempt>> = studyDao.getAllAttempts()
    val allBookmarks: Flow<List<BookmarkedQuestion>> = studyDao.getAllBookmarks()

    suspend fun saveAttempt(scoreSectionA: Int) {
        val percentage = (scoreSectionA.toFloat() / 10f) * 100f
        val attempt = QuizAttempt(
            scoreSectionA = scoreSectionA,
            totalSectionA = 10,
            percentage = percentage
        )
        studyDao.insertAttempt(attempt)
    }

    suspend fun toggleBookmark(question: QuestionItem) {
        val bookmark = BookmarkedQuestion(
            questionId = question.id,
            section = question.section,
            questionNumber = question.questionNumber,
            questionText = question.questionText,
            modelAnswer = question.modelAnswer,
            chapter = question.chapter
        )
        studyDao.insertBookmark(bookmark)
    }

    suspend fun removeBookmark(questionId: String) {
        studyDao.deleteBookmark(questionId)
    }

    fun isBookmarked(questionId: String): Flow<Boolean> = studyDao.isBookmarked(questionId)

    // --- Static Model Test Paper Data (40 Marks) ---
    fun getModelPaperQuestions(): List<QuestionItem> {
        return listOf(
            // Section A: Q1-Q2 (Match the Following)
            QuestionItem(
                id = "secA_q1",
                questionNumber = 1,
                section = "A",
                chapter = "પ્રકરણ ૩ (ઇતિહાસ)",
                questionType = QuestionType.MATCH,
                questionText = "(૧) મોહેં-જો-દડો",
                options = listOf(
                    QuestionOption("જાહેર સ્નાનાગાર અને ગટર યોજના (સિંધુ સંસ્કૃતિ)", true),
                    QuestionOption("ભરતમુનિનું નાટ્યશાસ્ત્ર", false),
                    QuestionOption("ગુજરાતનું અગત્યનું પ્રાચીન બંદર", false)
                ),
                correctValue = "જાહેર સ્નાનાગાર અને ગટર યોજના (સિંધુ સંસ્કૃતિ)",
                modelAnswer = "(૧) મોહેં-જો-દડો → જાહેર સ્નાનાગાર અને ગટર યોજના",
                marks = 1
            ),
            QuestionItem(
                id = "secA_q2",
                questionNumber = 2,
                section = "A",
                chapter = "પ્રકરણ ૨ (ઇતિહાસ)",
                questionType = QuestionType.MATCH,
                questionText = "(૨) સંગીત રત્નાકર",
                options = listOf(
                    QuestionOption("પંડિત સારંગદેવ રચિત સંગીત ગ્રંથ", true),
                    QuestionOption("ગુજરાતનું અગત્યનું પ્રાચીન બંદર", false),
                    QuestionOption("ભરતમુનિનું નાટ્યશાસ્ત્ર", false)
                ),
                correctValue = "પંડિત સારંગદેવ રચિત સંગીત ગ્રંથ",
                modelAnswer = "(૨) સંગીત રત્નાકર → પંડિત સારંગદેવ રચિત સંગીત ગ્રંથ",
                marks = 1
            ),

            // Section A: Q3-Q4 (True or False)
            QuestionItem(
                id = "secA_q3",
                questionNumber = 3,
                section = "A",
                chapter = "પ્રકરણ ૮ (ભૂગોળ)",
                questionType = QuestionType.TRUE_FALSE,
                questionText = "(૩) કાળી જમીનને 'રેગુર' જમીન તરીકે પણ ઓળખવામાં આવે છે.",
                options = listOf(
                    QuestionOption("ખરું", true),
                    QuestionOption("ખોટું", false)
                ),
                correctValue = "ખરું",
                modelAnswer = "ખરું (કાળી જમીન સ્વયં ખેડાતી અને કપાસ માટે શ્રેષ્ઠ હોવાથી રેગુર કહેવાય છે)",
                marks = 1
            ),
            QuestionItem(
                id = "secA_q4",
                questionNumber = 4,
                section = "A",
                chapter = "પ્રકરણ ૧૫ (અર્થશાસ્ત્ર)",
                questionType = QuestionType.TRUE_FALSE,
                questionText = "(૪) ભારતમાં આર્થિક વિકાસના પરિણામે લોકોના જીવનધોરણમાં કોઈ સુધારો થયો નથી.",
                options = listOf(
                    QuestionOption("ખરું", false),
                    QuestionOption("ખોટું", true)
                ),
                correctValue = "ખોટું",
                modelAnswer = "ખોટું (આર્થિક વિકાસથી લોકોની આવક વધે છે અને જીવનધોરણમાં સુધારો થાય છે)",
                marks = 1
            ),

            // Section A: Q5-Q6 (Fill in Blanks / One word)
            QuestionItem(
                id = "secA_q5",
                questionNumber = 5,
                section = "A",
                chapter = "પ્રકરણ ૯ (ભૂગોળ)",
                questionType = QuestionType.FILL_BLANK,
                questionText = "(૫) ગુજરાતમાં એશિયાઈ સિંહોનું એકમાત્ર નિવાસસ્થાન કયું રાષ્ટ્રીય ઉદ્યાન છે?",
                hint = "સંકેત: ગી_ _ રાષ્ટ્રીય ઉદ્યાન",
                correctValue = "ગીર રાષ્ટ્રીય ઉદ્યાન",
                modelAnswer = "ગીર રાષ્ટ્રીય ઉદ્યાન (ગુજરાત)",
                marks = 1
            ),
            QuestionItem(
                id = "secA_q6",
                questionNumber = 6,
                section = "A",
                chapter = "પ્રકરણ ૧૫ (અર્થશાસ્ત્ર)",
                questionType = QuestionType.FILL_BLANK,
                questionText = "(૬) ઉત્પાદનનું કુદરતી અને મફત સાધન કયું ગણાય છે?",
                hint = "સંકેત: જ_ી_ (Land)",
                correctValue = "જમીન",
                modelAnswer = "જમીન (Land)",
                marks = 1
            ),

            // Section A: Q7-Q10 (MCQs)
            QuestionItem(
                id = "secA_q7",
                questionNumber = 7,
                section = "A",
                chapter = "પ્રકરણ ૧ (ઇતિહાસ)",
                questionType = QuestionType.MCQ,
                questionText = "(૭) ભારતની દ્રવિડ પ્રજામાં કઈ કુટુંબપ્રથા પ્રચલિત હતી?",
                options = listOf(
                    QuestionOption("પિતૃપ્રધાન", false),
                    QuestionOption("માતૃપ્રધાન (માતૃમૂલક)", true)
                ),
                correctValue = "માતૃપ્રધાન (માતૃમૂલક)",
                modelAnswer = "માતૃપ્રધાન (માતૃમૂલક)",
                marks = 1
            ),
            QuestionItem(
                id = "secA_q8",
                questionNumber = 8,
                section = "A",
                chapter = "પ્રકરણ ૩ (ઇતિહાસ)",
                questionType = QuestionType.MCQ,
                questionText = "(૮) 'લોથલ' કયા જિલ્લામાં આવેલું પ્રાચીન સ્થાપત્ય-બંદર છે?",
                options = listOf(
                    QuestionOption("અમદાવાદ જિલ્લાના ધોળકા તાલુકામાં", true),
                    QuestionOption("કચ્છ જિલ્લાના ભચાઉ તાલુકામાં", false)
                ),
                correctValue = "અમદાવાદ જિલ્લાના ધોળકા તાલુકામાં",
                modelAnswer = "અમદાવાદ જિલ્લાના ધોળકા તાલુકામાં",
                marks = 1
            ),
            QuestionItem(
                id = "secA_q9",
                questionNumber = 9,
                section = "A",
                chapter = "પ્રકરણ ૮ (ભૂગોળ)",
                questionType = QuestionType.MCQ,
                questionText = "(૯) પડખાઉં (લેટેરાઇટ) જમીનનું નામ કયા લેટિન શબ્દ પરથી પડ્યું છે?",
                options = listOf(
                    QuestionOption("Later (ઈંટ)", true),
                    QuestionOption("Humus (જૈવિક દ્રવ્ય)", false)
                ),
                correctValue = "Later (ઈંટ)",
                modelAnswer = "Later (ઈંટ)",
                marks = 1
            ),
            QuestionItem(
                id = "secA_q10",
                questionNumber = 10,
                section = "A",
                chapter = "પ્રકરણ ૧૫ (અર્થશાસ્ત્ર)",
                questionType = QuestionType.MCQ,
                questionText = "(૧૦) જે અર્થતંત્રમાં ઉત્પાદનના સાધનોની માલિકી ખાનગી કે સ્વતંત્ર હોય તેને કઈ પદ્ધતિ કહે છે?",
                options = listOf(
                    QuestionOption("બજાર પદ્ધતિ (મૂડીવાદી)", true),
                    QuestionOption("સમાજવાદી પદ્ધતિ", false)
                ),
                correctValue = "બજાર પદ્ધતિ (મૂડીવાદી)",
                modelAnswer = "બજાર પદ્ધતિ (મૂડીવાદી)",
                marks = 1
            ),

            // Section B: Short Answer Questions (2 Marks each)
            QuestionItem(
                id = "secB_q11",
                questionNumber = 11,
                section = "B",
                chapter = "પ્રકરણ ૧ (ઇતિહાસ)",
                questionType = QuestionType.SHORT_ANSWER,
                questionText = "ભારતની પ્રાચીન પ્રજા 'નેગ્રીટો' (હબસી) વિશે ટૂંકનોંધ લખો.",
                modelAnswer = "નેગ્રીટો અથવા નિગ્રો (હબસી) ભારતની સૌથી પ્રાચીન પ્રજા માનવામાં આવે છે. તેઓ આફ્રિકામાંથી બલુચિસ્તાનના રસ્તે થઈને ભારતમાં આવ્યા હતા. તેમનો શારીરિક વર્ણ શ્યામ, ઊંચાઈ ૪ થી ૫ ફૂટ અને વાંકડિયા વાળ એ તેમની મુખ્ય શારીરિક લાક્ષણિકતાઓ હતી.",
                bulletPoints = listOf(
                    "ભારતની સૌથી પ્રાચીન પ્રજા પૈકીની એક.",
                    "આફ્રિકાથી બલુચિસ્તાનના માર્ગે ભારતમાં આગમન.",
                    "શારીરિક લક્ષણો: શ્યામ વર્ણ, ૪ થી ૫ ફૂટ ઊંચાઈ, વાંકડિયા વાળ."
                ),
                marks = 2
            ),
            QuestionItem(
                id = "secB_q12",
                questionNumber = 12,
                section = "B",
                chapter = "પ્રકરણ ૨ (ઇતિહાસ)",
                questionType = QuestionType.SHORT_ANSWER,
                questionText = "\"કાચી માટી અને પકવેલી માટી (ટેરાકોટા) ના વાસણો બનાવવા માટે ભારત પ્રાચીન કાળથી પ્રખ્યાત છે.\" - વિધાન સમજાવો.",
                modelAnswer = "પ્રાચીન સમયમાં માનવજીવન અને માટીને ગાઢ સંબંધ રહ્યો છે. ધાતુની શોધ નહોતી થઈ ત્યારે વાસણો માટીમાંથી બનતા. પકવેલી માટીને 'ટેરાકોટા' કહેવાય છે. નાગાર્જુનકોંડા (દક્ષિણ ભારત) અને મહેસાણાના 'લાંઘણજ' માંથી પ્રાચીન માટીના વાસણોના અવશેષો મળી આવ્યા છે જે માટીકામ કળાની પ્રાચીનતા સિદ્ધ કરે છે.",
                bulletPoints = listOf(
                    "માનવજીવન અને માટીકામનો સદીઓ જૂનો સગપણ સંબંધ.",
                    "પકવેલી માટીને 'ટેરાકોટા' (Terracotta) તરીકે ઓળખવામાં આવે છે.",
                    "દક્ષિણ ભારતનું નાગાર્જુનકોંડા અને ગુજરાતનું 'લાંઘણજ' (મહેસાણા) પ્રાચીન માટીકામ સ્થાપત્યોના અગત્યના પુરાવા આપે છે."
                ),
                marks = 2
            ),
            QuestionItem(
                id = "secB_q13",
                questionNumber = 13,
                section = "B",
                chapter = "પ્રકરણ ૮ (ભૂગોળ)",
                questionType = QuestionType.SHORT_ANSWER,
                questionText = "જમીન ધોવાણ એટલે શું? જમીન ધોવાણ અટકાવવાના ઉપાયો જણાવો.",
                modelAnswer = "અર્થ: વહેતા પાણી કે પવન દ્વારા જમીનના ઉપલા કણોનું એક જગ્યાએથી બીજી જગ્યાએ ઝડપથી ઘસડાઈ જવું એટલે જમીન ધોવાણ.\nઅટકાવવાના ઉપાયો: (૧) ઢાળવાળી જમીન પર પગથિયાં જેવાં ચાસ બનાવી વાવેતર કરવું. (૨) જમીન પર ચરાણ પ્રવૃત્તિઓ નિયંત્રિત કરવી. (૩) વૃક્ષારોપણ કરવું.",
                bulletPoints = listOf(
                    "જમીન ધોવાણ: પાણી કે પવન દ્વારા જમીનના સુક્ષ્મ કણોનું સ્થળાંતર.",
                    "અટકાવવા ઢાળવાળી જમીનમાં પગથિયા ચાસ (Contour farming) પદ્ધતિ અપનાવવી.",
                    "પડતર જમીનોમાં વૃક્ષારોપણ કરવું અને પશુ ચરાણ અટકાવવું."
                ),
                marks = 2
            ),
            QuestionItem(
                id = "secB_q14",
                questionNumber = 14,
                section = "B",
                chapter = "પ્રકરણ ૯ (ભૂગોળ)",
                questionType = QuestionType.SHORT_ANSWER,
                questionText = "જંગલોના વિનાશની (નિર્વનીકરણની) મુખ્ય અસરો જણાવો.",
                modelAnswer = "(૧) વાતાવરણમાં કાર્બન ડાયોક્સાઈડનું પ્રમાણ વધે છે. (૨) ગ્રીનહાઉસ અસરો (ગ્લોબલ વોર્મિંગ) ની તીવ્રતા વધે છે. (૩) જમીનનું ધોવાણ થાય છે અને વરસાદનું પ્રમાણ ઘટે છે. (૪) વન્યજીવો પોતાના કુદરતી આશ્રયસ્થાનો ગુમાવતા માનવ વસવાટો તરફ આવી ચડે છે.",
                bulletPoints = listOf(
                    "કાર્બન ડાયોક્સાઇડ (CO2) વધતાં તાપમાન વૃદ્ધિ અને ગ્લોબલ વોર્મિંગ.",
                    "વરસાદનું પ્રમાણ ઘટવું અને જમીનનું ધોવાણ વધવું.",
                    "વન્યજીવો કુદરતી આશ્રય ગુમાવી માનવ વસવાટ તરફ પ્રયાણ કરે છે."
                ),
                marks = 2
            ),
            QuestionItem(
                id = "secB_q15",
                questionNumber = 15,
                section = "B",
                chapter = "પ્રકરણ ૧૫ (અર્થશાસ્ત્ર)",
                questionType = QuestionType.SHORT_ANSWER,
                questionText = "'આર્થિક વિકાસ' અને 'આર્થિક વૃદ્ધિ' વચ્ચેનો મુખ્ય તફાવત સ્પષ્ટ કરો.",
                modelAnswer = "આર્થિક વૃદ્ધિ: તે પરિણામાત્મક ફેરફાર છે. વિકસિત દેશોની રાષ્ટ્રીય આવકમાં થતો વધારો 'આર્થિક વૃદ્ધિ' ગણાય છે.\nઆર્થિક વિકાસ: તે ગુણાત્મક અને પરિણામાત્મક બંને ફેરફાર છે. વિકાસશીલ દેશોની આવક વધવા સાથે લોકોનું જીવનધોરણ સુધરે તેને 'આર્થિક વિકાસ' કહે છે.",
                bulletPoints = listOf(
                    "આર્થિક વૃદ્ધિ (Economic Growth): પરિણામાત્મક (Quantitative) - વિકસિત દેશો માટે.",
                    "આર્થિક વિકાસ (Economic Development): ગુણાત્મક + પરિણામાત્મક - વિકાસશીલ દેશો માટે.",
                    "આર્થિક વિકાસથી રાષ્ટ્રીય આવક વધવા સાથે ગરીબી, ભૂખમરો ઘટે છે અને જીવનધોરણ સુધરે છે."
                ),
                marks = 2
            ),

            // Section C: Medium Answer Questions (3 Marks each)
            QuestionItem(
                id = "secC_q16",
                questionNumber = 16,
                section = "C",
                chapter = "પ્રકરણ ૨ (ઇતિહાસ)",
                questionType = QuestionType.MEDIUM_ANSWER,
                questionText = "પ્રાચીન ભારતની 'નાટ્યકળા' અને 'ભવાઈ' વિશે ટૂંકનોંધ લખો.",
                modelAnswer = "નાટ્યકળા: ભરતમુનિ રચિત 'નાટ્યશાસ્ત્ર' નાટ્યકળા ક્ષેત્રે અતિ પ્રખ્યાત છે. \"દ્રશ્ય-શ્રાવ્ય અને અભિનયના ત્રિવેણી સંગમ\" સાથે મનોરંજન અને જ્ઞાન આપતી આ કળા છે. મહાકવિ ભાસના 'દૂતવાક્યમ્' અને કાલિદાસનું 'અભિજ્ઞાન શાકુંતલમ્' અમર નાટકો છે.\nભવાઈ: અસાહિત ઠાકર દ્વારા શરૂ કરાયેલી ભવાઈ ગુજરાતની ૭૦૦ વર્ષ જૂની વિશિષ્ટ નાટ્યશૈલી છે. તે ઓછા ખર્ચે લોકશિક્ષણ અને મનોરંજન પૂરું પાડે છે. તેમાં 'રંગલા-રંગલી' ના પાત્રો મુખ્ય આકર્ષણ હોય છે.",
                bulletPoints = listOf(
                    "નાટ્યકળા: દ્રશ્ય, શ્રાવ્ય અને અભિનયનો ત્રિવેણી સંગમ.",
                    "ભરતમુનિનું નાટ્યશાસ્ત્ર અને કાલિદાસના અભિજ્ઞાન શાકુંતલમ્ જેવા મહાન ગ્રંથો.",
                    "ભવાઈ: અસાહિત ઠાકર દ્વારા પ્રારંભ, ૭૦૦ વર્ષ જૂની લોકનાટ્ય કળા.",
                    "રંગલા-રંગલીના પાત્રો દ્વારા સામાજિક કુરિવાજો પર કટાક્ષ અને લોકજાગૃતિ."
                ),
                marks = 3
            ),
            QuestionItem(
                id = "secC_q17",
                questionNumber = 17,
                section = "C",
                chapter = "પ્રકરણ ૩ (ઇતિહાસ)",
                questionType = QuestionType.MEDIUM_ANSWER,
                questionText = "\"લોથલ ભારતનું અગત્યનું પ્રાચીન બંદર અને ઔદ્યોગિક નગર હતું.\" - વિધાન સમજાવો.",
                modelAnswer = "૧. લોથલ અમદાવાદ જિલ્લાના ધોળકા તાલુકામાં ભોગાવો અને સાબરમતી નદીઓની વચ્ચે આવેલું છે.\n૨. અહીંથી ઈંટોનું બનેલું એક મોટું માળખું મળી આવ્યું છે, જેને 'ધક્કો' (Dockyard) માનવામાં આવે છે. વાહનોને લંગારીને માલસામાનની હેરફેર માટે આ ધક્કો વપરાતો.\n૩. અહીંથી મણકા બનાવવાની ફેક્ટરી, વખારો (Storehouses) અને સીલ (મુદ્રાઓ) મળી આવ્યા છે, જે દર્શાવે છે કે લોથલ પ્રાચીન ભારતનું આંતરરાષ્ટ્રીય વેપારી બંદર હતું.",
                bulletPoints = listOf(
                    "સ્થાન: અમદાવાદ જિલ્લો, ધોળકા તાલુકો, ભોગાવો-સાબરમતી વચ્ચે.",
                    "ગોદી / ધક્કો (Dockyard): ઈંટોની બનેલી રચના જ્યાં વહાણો લંગારાતા.",
                    "ઔદ્યોગિક પુરાવા: મણકા બનાવવાનું કારખાનું, વખારો અને વેપારી મુદ્રાઓ."
                ),
                marks = 3
            ),
            QuestionItem(
                id = "secC_q18",
                questionNumber = 18,
                section = "C",
                chapter = "પ્રકરણ ૮ (ભૂગોળ)",
                questionType = QuestionType.MEDIUM_ANSWER,
                questionText = "કાળી જમીન (રેગુર) ના લક્ષણો અને તેની ફળદ્રુપતા વર્ણવો.",
                modelAnswer = "ઉદ્ભવ: દખ્ખણના લાવાના પથરાવાથી આ જમીન બની છે. મહારાષ્ટ્ર, પશ્ચિમ મધ્ય પ્રદેશ અને ગુજરાતમાં આ જમીન આવેલી છે.\nલક્ષણો: કાળી જમીન ચીકની અને કાળી હોય છે. તે ભેજસંગ્રહ શક્તિ ખૂબ વધારે ધરાવે છે. ભેજ સુકાતાં તેમાં ઊંડી તિરાડો પડે છે.\nપાક: આ જમીન કપાસના પાક માટે અતિ અનુકૂળ હોવાથી તેને 'કપાસની જમીન' પણ કહેવાય છે. ઉપરાંત મગફળી, તમાકુ અને શેરડી સારા પ્રમાણમાં થાય છે.",
                bulletPoints = listOf(
                    "ઉદ્ભવ: દક્ષિણ ભારત (દખ્ખણ) ના લાવા પથરાવાથી બનેલી જમીન.",
                    "ગુણધર્મો: ચીકની, ઊંડી અને ભેજસંગ્રહ કરવાની ઊંચી ક્ષમતા.",
                    "સુકાતાં જમીનમાં સ્વયં તિરાડો (ખેડાણ જેવી સ્થિતિ) પડે છે.",
                    "મુખ્ય પાક: કપાસ (કપાસની કાળી જમીન), મગફળી, શેરડી, તમાકુ."
                ),
                marks = 3
            ),
            QuestionItem(
                id = "secC_q19",
                questionNumber = 19,
                section = "C",
                chapter = "પ્રકરણ ૯ (ભૂગોળ)",
                questionType = QuestionType.MEDIUM_ANSWER,
                questionText = "વન્યજીવ સંરાક્ષણ માટે હાથ ધરાયેલી 'વાઘ પરિયોજના' (Project Tiger) વિશે સમજૂતી આપો.",
                modelAnswer = "૧. ૨૦મી સદીની શરૂઆતમાં ભારતમાં આશરે ૪૦,૦૦૦ કરતાં વધુ વાઘ હતા. પરંતુ અનિયંત્રિત શિકાર અને નિર્વનીકરણથી વાઘની સંખ્યામાં ભારે ઘટાડો થયો.\n૨. વાઘની પ્રજાતિને બચાવવા માટે દેશમાં વર્ષ ૧૯૭૧ માં 'પ્રોજેક્ટ ટાઇગર' (વાઘ પરિયોજના) શરૂ કરવામાં આવી.\n૩. આ પરિયોજના હેઠળ વાઘના કુદરતી નિવાસસ્થાનો સુરક્ષિત કરાયા. હાલ દેશમાં આશરે ૫૦ થી વધુ ક્ષેત્રોમાં આ યોજના કાર્યરત છે.",
                bulletPoints = listOf(
                    "પૃષ્ઠભૂમિ: ૨૦મી સદીના પ્રારંભે ૪૦,૦૦૦+ વાઘમાંથી બેફામ શિકારને લીધે વાઘ લુપ્ત થવાના આરે આવ્યા.",
                    "પ્રારંભ: વર્ષ ૧૯૭૧ માં ભારત સરકાર દ્વારા વાઘ પરિયોજના શરૂ કરાઈ.",
                    "ઉદ્દેશ: વાઘના કુદરતી આશ્રયસ્થાનો સુરક્ષિત કરવા અને માનવીય હસ્તક્ષેપ અટકાવવો.",
                    "વિસ્તાર: દેશમાં હાલ ૫૦ થી વધુ સંરક્ષિત વિસ્તારોમાં કાર્યરત."
                ),
                marks = 3
            ),

            // Section D: Long Answer Question (4 Marks)
            QuestionItem(
                id = "secD_q20",
                questionNumber = 20,
                section = "D",
                chapter = "પ્રકરણ ૩ (ઇતિહાસ)",
                questionType = QuestionType.LONG_ANSWER,
                questionText = "પ્રાચીન ભારતનું નગર આયોજન: મોહેં-જો-દડોની નગરરચનાના 'રસ્તાઓ' અને 'ગટર યોજના' વિશે સવિસ્તાર માહિતી આપો.",
                modelAnswer = "૧. રસ્તાઓ: મોહેં-જો-દડોના રસ્તાઓ ૯.૭૫ મીટર પહોળા અને સુવ્યવસ્થિત હતા. નાગા રસ્તાઓ ઉત્તરથી દક્ષિણ અને પૂર્વથી પશ્ચિમ તરફ જતાં એકબીજાને કાટખૂણે કાપતા હતા. રસ્તાઓની બાજુમાં રાત્રિપ્રકાશ માટે થાંભલા હોવાના પુરાવા મળ્યા છે.\n\n૨. ગટર યોજના: આ નગરરચનાનું સૌથી વિશિષ્ટ પાસું તેની ગટર યોજના હતી. આવી ગટર યોજના સમકાલીન ભૂમધ્ય સમુદ્રના 'ક્રીટ' ટાપુ સિવાય ક્યાંય જોવા મળતી નથી.\n\n૩. રચના: દરેક મકાનનું ગંદુ પાણી નાની ગટરમાંથી મોટી ગટરમાં જતું. ગટરમાં અમુક અંતરે કચરો એકઠો કરવા માટે 'ખાડકૂવા' બનાવવામાં આવ્યા હતા.\n\n૪. તારણ: આયોજનબદ્ધ ગટર યોજના દર્શાવે છે કે તે સમયે સ્વચ્છતા અને જાહેર આરોગ્ય પ્રત્યે લોકો ખૂબ જાગ્રત હતા.",
                bulletPoints = listOf(
                    "રસ્તાઓની પહોળાઈ: ૯.૭૫ મીટર અતિ સુવિધાયુક્ત પહોળા રસ્તાઓ.",
                    "કાટખૂણે કાપતા રસ્તા: ઉત્તર-દક્ષિણ અને પૂર્વ-પશ્ચિમ મુખ્ય રાજમાર્ગો.",
                    "રાત્રિપ્રકાશ વ્યવસ્થા: રસ્તાઓની બંને બાજુએ થાંભલાના અવશેષો.",
                    "ગટર યોજનાની વિશિષ્ટતા: ભૂમધ્ય સમુદ્રના 'ક્રીટ' ટાપુ જેવી અજોડ રચના.",
                    "ખાડકૂવા સિસ્ટમ: કચરો અને ગંદુ પાણી નિકાલ માટે આયોજનબદ્ધ ખાડકૂવા.",
                    "સામાજિક સંદેશ: જાહેર સ્વચ્છતા અને આરોગ્ય પ્રત્યે પ્રાચીન ભારતીયોની ઉચ્ચ જાગૃતિ."
                ),
                marks = 4
            ),

            // Section D: Map Practice Question (4 Marks)
            QuestionItem(
                id = "secD_q21",
                questionNumber = 21,
                section = "D",
                chapter = "પ્રકરણ ૮ & ૯ (ભૂગોળ)",
                questionType = QuestionType.MAP_POINT,
                questionText = "ભારતના રેખાંકિત નક્શામાં નીચેની વિગતો યોગ્ય સંજ્ઞા વડે દર્શાવો:",
                modelAnswer = "📍 નક્શાપૂર્તિ ચાવીઓ:\n• (A) કાળી જમીન (રેગુર): મહારાષ્ટ્ર/ગુજરાતમાં કપાસના પ્રદેશ પર સંજ્ઞા દર્શાવવી.\n• (B) કાંપની જમીન: ગંગા-યમુનાના મેદાન (ઉત્તર પ્રદેશ/પંજાબ/બિહાર) દર્શાવવું.\n• (C) ગીર રાષ્ટ્રીય ઉદ્યાન: ગુજરાતના સૌરાષ્ટ્ર ભાગના નીચેના છેડે જૂનાગઢ/ગીર સોમનાથ પોઇન્ટ કરવો.\n• (D) કાઝીરંગા રાષ્ટ્રીય ઉદ્યાન: ભારતના પૂર્વોત્તર રાજ્ય અસમના મધ્ય ભાગમાં પોઇન્ટ દર્શાવવો.",
                bulletPoints = listOf(
                    "(A) કાળી જમીન → ગુજરાત / મહારાષ્ટ્ર",
                    "(B) કાંપની જમીન → પંજાબ / ઉત્તર પ્રદેશ / બિહાર ગંગાનું મેદાન",
                    "(C) ગીર રાષ્ટ્રીય ઉદ્યાન → ગુજરાત (જૂનાગઢ/ગીર સોમનાથ)",
                    "(D) કાઝીરંગા રાષ્ટ્રીય ઉદ્યાન → અસમ (એકશિંગી ભારતીય ગેંડો)"
                ),
                marks = 4
            )
        )
    }

    // --- Chapter Summaries ---
    fun getChapters(): List<ChapterInfo> {
        return listOf(
            ChapterInfo(
                number = 1,
                titleGujarati = "ભારતનો વારસો",
                titleEnglish = "India's Heritage",
                subjectCategory = "ઇતિહાસ (History)",
                overview = "ભારતના પ્રાચીન વારસા, પ્રાકૃતિક અને સાંસ્કૃતિક વારસાના અંગો, અને ભારતમાં આવેલી પ્રાચીન પ્રજાઓ (નેગ્રીટો, ઓસ્ટ્રેલોઇડ, દ્રવિડ, આર્યો) વિશેની સંપૂર્ણ સમજૂતી.",
                keyPoints = listOf(
                    "વિષ્ણુ પુરાણમાં ભારતનું વર્ણન: 'ઉત્તરં યત્સમુદ્રસ્ય હિમાદ્રેશ્ચૈવ દક્ષિણમ્...'",
                    "પ્રાકૃતિક વારસો: નદીઓ, પર્વતો, વૃક્ષો, વનસ્પતિ અને વન્યજીવો.",
                    "પ્રાચીન પ્રજાઓ: નેગ્રીટો (હબસી), ઓસ્ટ્રેલોઇડ (નિષાદ), દ્રવિડ (માતૃપ્રધાન કુટુંબપ્રથા), આર્યો (નોર્ડિક)."
                ),
                impTopics = listOf(
                    "નેગ્રીટો અને ઓસ્ટ્રેલોઇડ પ્રજાની સરખામણી",
                    "પ્રાકૃતિક વારસો અને સાંસ્કૃતિક વારસા વચ્ચેનો તફાવત",
                    "ગુજરાતનો સાંસ્કૃતિક વારસો અને મેળાઓ"
                )
            ),
            ChapterInfo(
                number = 2,
                titleGujarati = "ભારતનો સાંસ્કૃતિક વારસો: પરંપરાઓ: હસ્ત અને લલિતકળા",
                titleEnglish = "Cultural Heritage: Art & Craft",
                subjectCategory = "ઇતિહાસ (History)",
                overview = "માટીકામ કળા, ભરતગુથણ, ચર્મકામ, હીરા-માણિક અને મોતીકામ, સંગીત કળા, નાટ્યકળા, અને ગુજરાતના લોકનૃત્યોની વિસ્તૃત સમજ.",
                keyPoints = listOf(
                    "માટીકામ: પકવેલી માટીને 'ટેરાકોટા' કહે છે (લાંઘણજ, નાગાર્જુનકોંડા).",
                    "સંગીત કળા: સામવેદને સંગીતની ગંગોત્રી કહેવાય છે. ગ્રંથો: સંગીત રત્નાકર (પંડિત સારંગદેવ), સંગીત મકરંદ, સંગીત પારીજાત.",
                    "નાટ્યકળા: ભરતમુનિનું નાટ્યશાસ્ત્ર, કાલિદાસનું અભિજ્ઞાન શાકુંતલમ્.",
                    "ભવાઈ: અસાહિત ઠાકર દ્વારા પ્રારંભ, ૭૦૦ વર્ષ જૂની લોકનાટ્ય કળા."
                ),
                impTopics = listOf(
                    "પ્રાચીન ભારતનું માટીકામ અને ટેરાકોટા",
                    "સંગીત કળાના સંગીત ગ્રંથોની માહિતી",
                    "ભવાઈ કળા અને તેના પાત્રો (રંગલા-રંગલી)"
                )
            ),
            ChapterInfo(
                number = 3,
                titleGujarati = "ભારતનો સાંસ્કૃતિક વારસો: શિલ્પ અને સ્થાપત્ય",
                titleEnglish = "Sculpture & Architecture",
                subjectCategory = "ઇતિહાસ (History)",
                overview = "સિંધુ ખીણની સંસ્કૃતિના નગરો (મોહેં-જો-દડો, હડપ્પા, લોથલ, ધોળાવીરા) ના નગર આયોજન, સ્નાનાગાર, ગટર યોજના અને ગુફા સ્થાપત્યની માહિતી.",
                keyPoints = listOf(
                    "મોહેં-જો-દડો: ૯.૭૫ મીટર પહોળા રસ્તાઓ, જાહેર સ્નાનાગાર અને ક્રીટ ટાપુ જેવી અદભુત ગટર યોજના.",
                    "લોથલ: અમદાવાદ જિલ્લાના ધોળકા તાલુકામાં. ધક્કો (Dockyard), મણકા ફેક્ટરી અને આંતરરાષ્ટ્રીય બંદર.",
                    "ધોળાવીરા: કચ્છ જિલ્લાના ભચાઉ તાલુકામાં મંજર ખદિરબેટમાં સ્થાપત્ય."
                ),
                impTopics = listOf(
                    "મોહેં-જો-દડોની રસ્તા અને ગટર યોજના (૪ માર્ક્સ IMP)",
                    "લોથલ પ્રાચીન બંદર અને ધક્કો (Dockyard)",
                    "સ્તૂપ એટલે શું? સાંચીનો સ્તૂપ"
                )
            ),
            ChapterInfo(
                number = 8,
                titleGujarati = "કુદરતી સંસાધનો",
                titleEnglish = "Natural Resources",
                subjectCategory = "ભૂગોળ (Geography)",
                overview = "જમીનના પ્રકારો (કાંપની જમીન, કાળી જમીન, રાતી જમીન, પડખાઉં જમીન), જમીન ધોવાણ અને જમીન સંરક્ષણના અગત્યના ઉપાયો.",
                keyPoints = listOf(
                    "કાળી જમીન (Regur): સ્વયં ખેડાતી જમીન, કપાસ માટે શ્રેષ્ઠ, લાવા પથરાવાથી બનેલી.",
                    "પડખાઉં જમીન (Laterite): લેટિન શબ્દ Later (ઈંટ) પરથી, લોહતત્વ અને એલ્યુમિનિયમથી સભર.",
                    "જમીન ધોવાણ: વહેતા પાણી કે પવનથી કણોનું સ્થળાંતર. અટકાવવા પગથિયાં ચાસ અને વૃક્ષારોપણ."
                ),
                impTopics = listOf(
                    "કાળી જમીન (રેગુર) ના લક્ષણો",
                    "જમીન ધોવાણ એટલે શું? તેના અટકાવવાના ઉપાયો",
                    "જમીન સંરક્ષણની વિવિધ પદ્ધતિઓ"
                )
            ),
            ChapterInfo(
                number = 9,
                titleGujarati = "વન અને વન્યજીવ સંસાધન",
                titleEnglish = "Forest & Wildlife Resources",
                subjectCategory = "ભૂગોળ (Geography)",
                overview = "જંગલોનું વર્ગીકરણ, નિર્વનીકરણની અસરો, વન્યજીવ સંરક્ષણ યોજનાઓ (વાઘ પરિયોજના, ગીર સિંહ યોજના) અને રાષ્ટ્રીય ઉદ્યાનો.",
                keyPoints = listOf(
                    "વાઘ પરિયોજના (Project Tiger): ૧૯૭૧ માં શરૂ, ૫૦ થી વધુ સંરક્ષિત વિસ્તારો.",
                    "ગીર રાષ્ટ્રીય ઉદ્યાન: ગુજરાતના સૌરાષ્ટ્રમાં એશિયાઈ સિંહોનું એશિયામાં એકમાત્ર નિવાસસ્થાન.",
                    "કાઝીરંગા રાષ્ટ્રીય ઉદ્યાન: અસમમાં એકશિંગી ભારતીય ગેંડા માટે પ્રખ્યાત."
                ),
                impTopics = listOf(
                    "વાઘ પરિયોજના (Project Tiger) ની ટૂંકનોંધ",
                    "નિર્વનીકરણ (Deforestation) ની અસરો",
                    "રાષ્ટ્રીય ઉદ્યાન અને અભયારણ્ય વચ્ચેનો તફાવત"
                )
            ),
            ChapterInfo(
                number = 15,
                titleGujarati = "આર્થિક વિકાસ",
                titleEnglish = "Economic Development",
                subjectCategory = "અર્થશાસ્ત્ર (Economics)",
                overview = "આર્થિક વિકાસ vs આર્થિક વૃદ્ધિ, ઉત્પાદનના સાધનો (જમીન, શ્રમ, મૂડી, નિયોજક), અને બજાર પદ્ધતિ vs સમાજવાદી પદ્ધતિ.",
                keyPoints = listOf(
                    "આર્થિક વિકાસ: રાષ્ટ્રીય આવક વધવાની સાથે લોકોના જીવનધોરણમાં થતો ગુણાત્મક સુધારો.",
                    "ઉત્પાદનના સાધનો: જમીન (કુદરતી મફત સાધન), શ્રમ, મૂડી, નિયોજક.",
                    "બજાર પદ્ધતિ (Market/Capitalist): ખાનગી માલિકી, નફાનો ઉદ્દેશ, મુક્ત હરીફાઈ.",
                    "સમાજવાદી પદ્ધતિ: રાજ્યની માલિકી, સમાજ કલ્યાણનો ઉદ્દેશ."
                ),
                impTopics = listOf(
                    "આર્થિક વૃદ્ધિ અને આર્થિક વિકાસનો તફાવત",
                    "ઉત્પાદનના સાધનોની વિગતો",
                    "બજાર પદ્ધતિના લક્ષણો અને મર્યાદાઓ"
                )
            )
        )
    }

    // --- Flashcards Data ---
    fun getFlashcards(): List<FlashcardItem> {
        return listOf(
            FlashcardItem(1, 1, "નેગ્રીટો (હબસી) ક્યાંથી ભારતમાં આવ્યા હતા?", "આફ્રિકામાંથી બલુચિસ્તાનના રસ્તે થઈને.", "ઇતિહાસ"),
            FlashcardItem(2, 1, "દ્રવિડ લોકોમાં કઈ કુટુંબપ્રથા હતી?", "માતૃપ્રધાન (માતૃમૂલક) કુટુંબપ્રથા.", "ઇતિહાસ"),
            FlashcardItem(3, 2, "પકવેલી માટીને શું કહેવામાં આવે છે?", "ટેરાકોટા (Terracotta).", "ઇતિહાસ"),
            FlashcardItem(4, 2, "સંગીત રત્નાકર ગ્રંથના રચયિતા કોણ હતા?", "પંડિત સારંગદેવ.", "ઇતિહાસ"),
            FlashcardItem(5, 2, "ભવાઈના પ્રણેતા અને કેટલા વર્ષ જૂની શૈલી છે?", "અસાહિત ઠાકર, ૭૦૦ વર્ષ જૂની લોકનાટ્ય કળા.", "ઇતિહાસ"),
            FlashcardItem(6, 3, "મોહેં-જો-દડોના મુખ્ય રસ્તાઓની પહોળાઈ કેટલી હતી?", "૯.૭૫ મીટર પહોળા રસ્તાઓ.", "ઇતિહાસ"),
            FlashcardItem(7, 3, "લોથલ કઈ નદીઓ વચ્ચે અને કયા તાલુકામાં આવેલું છે?", "ભોગાવો અને સાબરમતી નદીઓ વચ્ચે, ધોળકા તાલુકા (અમદાવાદ) માં.", "ઇતિહાસ"),
            FlashcardItem(8, 3, "લોથલમાં વહાણો લંગારવા ઈંટોનું બનેલું શું મળી આવ્યું છે?", "ધક્કો (Dockyard / ગોદી).", "ઇતિહાસ"),
            FlashcardItem(9, 8, "કાળી જમીનનું બીજું નામ કયું છે?", "રેગુર (Regur) જમીન.", "ભૂગોળ"),
            FlashcardItem(10, 8, "લેટેરાઇટ જમીનનું નામ કયા શબ્દ પરથી પડ્યું?", "લેટિન શબ્દ 'Later' (જેનો અર્થ ઈંટ થાય છે).", "ભૂગોળ"),
            FlashcardItem(11, 9, "ગીર રાષ્ટ્રીય ઉદ્યાન કયા પ્રાણી માટે પ્રખ્યાત છે?", "એશિયાઈ સિંહ (Asiatic Lion).", "ભૂગોળ"),
            FlashcardItem(12, 9, "વાઘ પરિયોજના (Project Tiger) ક્યારે શરૂ કરાઈ?", "વર્ષ ૧૯૭૧ માં.", "ભૂગોળ"),
            FlashcardItem(13, 9, "એકાશિંગી ભારતીય ગેંડો કયા રાષ્ટ્રીય ઉદ્યાનમાં જોવા મળે છે?", "કાઝીરંગા રાષ્ટ્રીય ઉદ્યાન (અસમ).", "ભૂગોળ"),
            FlashcardItem(14, 15, "ઉત્પાદનનું કુદરતી અને મફત સાધન કયું છે?", "જમીન (Land).", "અર્થશાસ્ત્ર"),
            FlashcardItem(15, 15, "ખાનગી માલિકી ધરાવતી અર્થવ્યવસ્થાને શું કહે છે?", "બજાર પદ્ધતિ (મૂડીવાદી પદ્ધતિ).", "અર્થશાસ્ત્ર")
        )
    }

    // --- Interactive Map Points Data ---
    fun getMapLocations(): List<MapLocation> {
        return listOf(
            MapLocation(
                id = "gir_lion",
                title = "ગીર રાષ્ટ્રીય ઉદ્યાન (Gir National Park)",
                category = "વહ્યજીવ સંરક્ષણ",
                regionText = "જૂનાગઢ / ગીર સોમનાથ, સૌરાષ્ટ્ર",
                state = "ગુજરાત",
                description = "સમગ્ર એશિયા ખંડમાં એશિયાઈ સિંહોનું (Asiatic Lions) એકમાત્ર કુદરતી નિવાસસ્થાન. વર્ષ ૧૯૭૫ માં રાષ્ટ્રીય ઉદ્યાન ઘોષિત કરાયું.",
                relativeX = 0.22f,
                relativeY = 0.52f
            ),
            MapLocation(
                id = "kaziranga",
                title = "કાઝીરંગા રાષ્ટ્રીય ઉદ્યાન (Kaziranga)",
                category = "વહ્યજીવ સંરક્ષણ",
                regionText = "ગોલાઘાટ અને નાગાંવ જિલ્લો",
                state = "અસમ",
                description = "એકશિંગી ભારતીય ગેંડા (One-horned Rhinoceros) ના સંરક્ષણ માટે વિશ્વભરમાં પ્રખ્યાત. યુનેસ્કો વર્લ્ડ હેરિટેજ સાઇટ.",
                relativeX = 0.82f,
                relativeY = 0.38f
            ),
            MapLocation(
                id = "black_soil",
                title = "કાળી જમીન (રેગુર - Black Soil)",
                category = "જમીનનો પ્રકાર",
                regionText = "સૌરાષ્ટ્ર, દખ્ખણનો લાવા પ્રદેશ, મહારાષ્ટ્ર",
                state = "ગુજરાત અને મહારાષ્ટ્ર",
                description = "ભેજસંગ્રહ કરવાની ઊંચી ક્ષમતા, કપાસના પાક માટે અતિ ઉત્તમ હોવાથી 'કપાસની કાળી જમીન' કહેવાય છે.",
                relativeX = 0.32f,
                relativeY = 0.58f
            ),
            MapLocation(
                id = "alluvial_soil",
                title = "કાંપની જમીન (Alluvial Soil)",
                category = "જમીનનો પ્રકાર",
                regionText = "ગંગા-યમુનાનું વિશાળ ઉત્તર મેદાન",
                state = "પંજાબ, ઉત્તર પ્રદેશ, બિહાર, પશ્ચિમ બંગાળ",
                description = "નદીઓના કાંપના નિક્ષેપણથી બનતી અતિ ફળદ્રુપ જમીન. ઘઉં, ડાંગર, શેરડી અને કઠોળ માટે ઉત્તમ.",
                relativeX = 0.55f,
                relativeY = 0.32f
            )
        )
    }
}
